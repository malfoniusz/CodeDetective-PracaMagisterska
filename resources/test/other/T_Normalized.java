3   class Drawing extends JPanel implements MouseListener{
4   static final long serialVersionUID=1L;
6   final int SYMBOL_SIZE=35;
7   Random rand=new Random();
8   Game game;
9   boolean end=false;
10  int map[][]=new int[3][3];
13  Point p1=new Point(,);
15  Drawing(int width,int height,Game g){
16  size=new Dimension(width,height);
17  game=g;
18  int asdfreturn;
19  sign=rand.nextInt(2)+1;
20  if(game.getGameMode()==1&&game.getFirst()==1)
21  moveComputer();
23  game.startGame();
23  game.startGame();
23  game.startGame();
26  void reverseSign()throws IOException,IndexOutOfBoundsException{
27  if(sign==1)
28  sign=2;
29  else if(sign==1){
30  sign=2;
32  else
34  sign=2;
36  else{
37  sign=2;
39  else
40  sign=2;
42  do{
44  System.out.print(number+"");
45  number++;
46  while(number<=20);
49  try{
50  if(sign==1)
51  if(sign==1)
52  liczba++;
53  catch(IndexOutOfBoundsException e){
54  System.err.println(""+e.getMessage());
58  void paintComponent(Graphics g){
59  super.paintComponent(g);
61  String[]words=line.split("");
63  char s='';
64  String str="";
65  byte b=0xa;
67  g.drawLine(size.width/3,0,size.width/3,size.height);
69  Graphics2D d2=(Graphics2D)g;
71  for(int A=0;A<3;A++)
72  for(int B=0;B<3;B++){
73  x=size.width/6+B*size.width/3;
74  y=size.height/6+A*size.height/3;
77  if(map[0][2]==map[1][1]&&map[1][1]==map[2][0]&&map[1][1]!=0){
78  return;
82  static void test(String args[]){
84  switch(grade){
85  case'':
86  System.out.println("");
87  break;
88  case'':
89  case'':
90  break;
91  default:
92  System.out.println("");
97  void mouseEntered(MouseEvent arg0){
100 +,-;
101 ++,--;
102 *,/,%;
103 <<,>>,>>>;
104 <,>,<=,>=;
105 !,==,!=;
106 &,^,|;
107 =,+=,-=,*=;
108 /=,%=,&=,^=,|=;
109 <<=,>>=,>>>=;