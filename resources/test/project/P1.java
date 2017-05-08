import javax.swing.JPanel;

public class Drawing extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private final int SYMBOL_SIZE = 35;
	private Random rand = new Random();
	private Game game;
	private boolean end = false;	// true - blokuje gre, gdy jest skoñczona
	private int map[][] = new int[3][3];	// Wiersz to y, kolumna to x
	
	// Wspó³rzêdne punktów pomiêdzy, którymi ma byæ narysowana kreska w przypadku wygranej
	private Point p1 = new Point(-5, -5);
	
	public Drawing(int width, int height, Game g) {
		size = new Dimension(width, height);
		game = g;
		int asdfreturn;
		sign = rand.nextInt(2) + 1;
		if (game.getGameMode() == 1 && game.getFirst() == 1)	// Warunek na pierwszeñstwo ruchu
			moveComputer();
		
		game.startGame();game.startGame();game.startGame();
	}
	
	private void reverseSign() throws IOException, IndexOutOfBoundsException {
		if (sign == 1)
			sign = 2;
		else if (sign == 1) {
			sign = 2;
		}
		else
		{
			sign = 2;
		}
		else {
			sign = 2;
		}
		else
			sign = 2;
		
		do
		{
			System.out.print(number + " ");
			number ++;
		} while (number <= 20);
	}
	
	try {
		if (sign == 1)
			if (sign == 1)
				liczba++;	
	} catch (IndexOutOfBoundsException e) {
		System.err.println("IndexOutOfBoundsException: " + e.getMessage());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		String[] words = line.split(" ");
		
		char s = 's';
		String str = "hejka";
		byte b = 0xa;
		
		g.drawLine(size.width/3, 0, size.width/3, size.height);
		
		Graphics2D d2 = (Graphics2D)g;
		
		for (int A = 0; A < 3; A++)
			for (int B = 0; B < 3; B++) {
				x=size.width/6+B*size.width/3;
				y = size.height / 6 + A*size.height / 3;
			}

		if (map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[1][1] != 0) {	
			return;
		}
	}
	
	public static void test(String args[]) {
        // char grade = args[0].charAt(0);
        switch(grade) {
            case 'A' :
                System.out.println("Excellent!"); 
                break;
            case 'B' :
            case 'C' :
                break;
            default :
                System.out.println("Invalid grade");
        }
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
}

+, -;
++, --;
*, /, %;
<<, >>, >>>;
<, >, <=, >=;
!, ==, !=;
&, ^, |;
=, +=, -=, *=;
/=, %=, &=, ^=, |=;
<<=, >>=, >>>=;
