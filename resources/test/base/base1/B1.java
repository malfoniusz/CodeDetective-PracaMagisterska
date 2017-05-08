package com.example.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.LexemeValue;
import net.sf.clipsrules.jni.MultifieldValue;
import net.sf.clipsrules.jni.StringValue;


class DietConsultant implements ActionListener {

	private enum InterviewState {
		GREETING, INTERVIEW, CONCLUSION
	};

	JLabel displayLabel;
	JButton nextButton;
	JButton printButton;
	JPanel choicesPanel;
	ButtonGroup choicesButtons;
	Environment clips;
	boolean isExecuting = false;
	Thread executionThread;
	String relationAsserted;
	ArrayList<String> variableAsserts;
	InterviewState interviewState;
	String summary;

	DietConsultant() {
		JFrame jfrm = new JFrame("System ekspertowy do konsultacji diety");
		jfrm.getContentPane().setLayout(new FlowLayout());
		jfrm.setSize(800, 600);
		jfrm.setLocationRelativeTo(null);
		jfrm.setResizable(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel displayPanel = new JPanel();
		displayLabel = new JLabel();
		displayPanel.add(displayLabel);
		choicesPanel = new JPanel();
		choicesButtons = new ButtonGroup();

		JPanel buttonPanel = new JPanel();
		nextButton = new JButton("Dalej");
		nextButton.setActionCommand("NEXT");
		nextButton.addActionListener(this);
		buttonPanel.add(nextButton);
		printButton = new JButton("Kopiuj do schowka");
		printButton.setActionCommand("PRINT");
		printButton.addActionListener(this);
		printButton.setVisible(false);
		buttonPanel.add(printButton);

		JPanel space1 = new JPanel();
		space1.setPreferredSize(new Dimension(2000, 10));
		JPanel space2 = new JPanel();
		space2.setPreferredSize(new Dimension(2000, 10));

		jfrm.getContentPane().add(displayPanel);
		jfrm.getContentPane().add(space1);
		jfrm.getContentPane().add(choicesPanel);
		jfrm.getContentPane().add(space2);
		jfrm.getContentPane().add(buttonPanel);

		variableAsserts = new ArrayList<String>();
		clips = new Environment();
		clips.loadFromResource("/com/example/knowledge/diet.clp");
		processRules();
		jfrm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (!isExecuting && ae.getActionCommand().equals("NEXT")) {
			switch (interviewState) {
				case GREETING:
					variableAsserts.add("(config (greeting skip))");
					break;
				case INTERVIEW:
					variableAsserts.add("(answer (id " + relationAsserted + ") (value " + choicesButtons.getSelection().getActionCommand() + "))");
					break;
				case CONCLUSION:
					System.out.println("Fakty zostały usunięte: " + variableAsserts.size());
					variableAsserts.clear();
					break;
			}
			processRules();
		} else if (!isExecuting && ae.getActionCommand().equals("PRINT")) {
			StringSelection selection = new StringSelection(summary);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		}
	}

	private void processRules() {
		clips.reset();
		for (String factString : variableAsserts) {
			clips.eval("(assert " + factString + ")");
			System.out.println("Dodaję do faktów: " + factString);
		}
		Runnable runThread = new Runnable() {
			@Override
			public void run() {
				clips.run();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							FactAddressValue fv = (FactAddressValue) ((MultifieldValue) clips.eval("(find-fact ((?f UI-state)) TRUE)")).get(0);
							if (fv.getFactSlot("state").toString().equals("greeting") && fv.getFactSlot("responded").toString().equals("TRUE")) {
								interviewState = InterviewState.CONCLUSION;
								nextButton.setText("Jeszcze raz");
								choicesPanel.setVisible(false);
								relationAsserted = null;
								MultifieldValue mv = (MultifieldValue) clips.eval("(find-all-facts ((?f deduction)) TRUE)");
								System.out.println("Ilość zaleceń: " + mv.size());
								if (mv.size() > 0) {
									String werdykt = "";
									String wWstep = "";
									String wAkapit1 = "";
									String wAkapit2 = "";
									String wAkapit3 = "";
									String wPodsumowanie = "";
									for (int i = 0; i < mv.size(); i++) {
										FactAddressValue fact = (FactAddressValue) mv.multifieldValue().get(i);
										String area = fact.getFactSlot("area").toString();
										if ("wstep".equals(area)) {
											wWstep += fact.getFactSlot("value").toString().replaceAll("\"","") + " ";
										} else if ("podsumowanie".equals(area)) {
											wPodsumowanie += fact.getFactSlot("value").toString().replaceAll("\"","") + " ";
										} else if ("akapit1".equals(area)) {
											wAkapit1 += fact.getFactSlot("value").toString().replaceAll("\"","") + " ";
										} else if ("akapit2".equals(area)) {
											wAkapit2 += ", " + fact.getFactSlot("value").toString().replaceAll("\"","");
										} else if ("akapit3".equals(area)) {
											wAkapit3 += fact.getFactSlot("value").toString().replaceAll("\"","") + " ";
										}
									}
									if (wAkapit2.length() > 0) {
										wAkapit2 = "Włącz do swojej diety następujące produkty: " + wAkapit2.replaceFirst(",", "") + ". ";
									}
									werdykt += wWstep + wAkapit1 + wAkapit2 + wAkapit3 + wPodsumowanie;
									wrapLabelText(displayLabel,
											"Odpowiedziałeś na wszystkie przygotowane dla Ciebie pytania. Dziękujemy![nl][nl]" + werdykt);
									summary = werdykt;
									printButton.setVisible(true);
								} else {
									wrapLabelText(displayLabel, "[nl][nl]W tym momencie nie wiemy jakie sugestie zaproponować. [nl]Prosimy udać się na konsultację do kwalifikowanego dietetyka.");
								}
							} else if (fv.getFactSlot("state").toString().equals("interview") && fv.getFactSlot("responded").toString().equals("FALSE")) {
								interviewState = InterviewState.INTERVIEW;
								nextButton.setText("Dalej");
								choicesPanel.removeAll();
								choicesButtons = new ButtonGroup();
								MultifieldValue damf = (MultifieldValue) fv.getFactSlot("labels");
								MultifieldValue vamf = (MultifieldValue) fv.getFactSlot("values");
								JRadioButton firstButton = null;
								for (int i = 0; i < damf.size(); i++) {
									LexemeValue da = (LexemeValue) damf.get(i);
									LexemeValue va = (LexemeValue) vamf.get(i);
									JRadioButton rButton;
									String buttonName, buttonAnswer;
									buttonName = da.lexemeValue();
									buttonAnswer = va.lexemeValue();
									rButton = new JRadioButton(buttonName, false);
									rButton.setActionCommand(buttonAnswer);
									choicesPanel.add(rButton);
									choicesButtons.add(rButton);
									if (firstButton == null) {
										firstButton = rButton;
									}
								}
								if ((choicesButtons.getSelection() == null) && (firstButton != null)) {
									choicesButtons.setSelected(firstButton.getModel(), true);
								}
								choicesPanel.repaint();
								relationAsserted = ((LexemeValue) fv.getFactSlot("relation-asserted")).lexemeValue();
								String theText = ((StringValue) fv.getFactSlot("display")).stringValue();
								wrapLabelText(displayLabel, theText);
								choicesPanel.setVisible(true);
							} else if (fv.getFactSlot("state").toString().equals("greeting")) {
								interviewState = InterviewState.GREETING;
								nextButton.setText("Rozpocznijmy");
								wrapLabelText(displayLabel, "[nl][nl]Witaj, poświęć chwilę czasu na odpowiedzi na kolejne pytania. [nl]Na zakończenie otrzymasz spersonalizowany raport.");
								choicesPanel.setVisible(false);
								printButton.setVisible(false);
								summary = "";
							} else {
								throw new Exception("INVALID STATE");
							}
							executionThread = null;
							isExecuting = false;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		};
		isExecuting = true;
		executionThread = new Thread(runThread);
		executionThread.start();
	}

	private void wrapLabelText(JLabel label, String text) {
		label.setText(String.format("<html><div WIDTH=750><center>%s</center></div><html>", text.replace("[nl]", "<br>")));
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DietConsultant();
			}
		});
	}
}
