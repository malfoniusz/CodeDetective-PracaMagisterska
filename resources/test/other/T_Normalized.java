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
18  sign=rand.nextInt(2)+1;
19  if(game.getGameMode()==1&&game.getFirst()==1)
20  moveComputer();
22  game.startGame();
22  game.startGame();
22  game.startGame();
25  void reverseSign()throws IOException,IndexOutOfBoundsException{
26  if(sign==1)
27  sign=2;
28  else if(sign==1){
29  sign=2;
31  else
33  sign=2;
35  else{
36  sign=2;
38  else
39  sign=2;
41  do{
43  System.out.print(number+"");
44  number++;
45  while(number<=20);
48  try{
49  if(sign==1)
50  if(sign==1)
51  liczba++;
52  catch(IndexOutOfBoundsException e){
53  System.err.println(""+e.getMessage());
57  void paintComponent(Graphics g){
58  super.paintComponent(g);
60  String[]words=line.split("");
62  char s='';
63  String str="";
64  byte b=0xa;
66  g.drawLine(size.width/3,0,size.width/3,size.height);
68  Graphics2D d2=(Graphics2D)g;
70  for(int A=0;A<3;A++)
71  for(int B=0;B<3;B++){
72  x=size.width/6+B*size.width/3;
75  if(map[0][2]==map[1][1]&&map[1][1]==map[2][0]&&map[1][1]!=0){
76  return;
80  static void test(String args[]){
82  switch(grade){
83  case'':
84  System.out.println("");
85  break;
86  case'':
87  case'':
88  break;
89  default:
90  System.out.println("");
95  void mouseEntered(MouseEvent arg0){
98 +,-;
99 ++,--;
100 *,/,%;
101 <<,>>,>>>;
102 <,>,<=,>=;
103 !,==,!=;
104 &,^,|;
105 =,+=,-=,*=;
106 /=,%=,&=,^=,|=;
107 <<=,>>=,>>>=;