package 法式;
import java.util.ArrayList;
import java.util.Scanner; 
public class Main {
	    public static void main(String[] args) {
	        // 输入字w
	        ArrayList<Word> inputList=new ArrayList<Word>();//存储输入字
	        ArrayList<Word> subNormalList=new ArrayList<Word>();//存储半法式
	        ArrayList<Word> normalList=new ArrayList<>();//存储法式
	        //输入任意字
	        inputList=getInputList();
	        System.out.println("输入数据为：");
	        printList(inputList);
	        System.out.println("【其中每组数字第一个数字代表下标，第二个数字1代表正字母，-1代表负字母】");
	        //按照NF1的要求进行排序，得到半法式
	        subNormalList=sort(inputList);
	        System.out.println("半法式为：");
	        printList(subNormalList);
	        //删除违规二元组得到法式
	        normalList=subNormalToNormal(subNormalList);
	        System.out.println("法式为：");
	        printList(normalList);
	    }
	    // 分为随机输入和手动输入两种
	    private static ArrayList<Word> getInputList() {  
	        ArrayList<Word> list=new ArrayList<Word>();
	        Scanner input =new Scanner(System.in);
	        System.out.println("if you want to input word please input y/Y first,else input any key and enter to input data auto");
	        char c=input.next().charAt(0);
	        if(c=='Y'||c=='y'){
	            System.out.println("请按以下格式输入数据：  a b 回车。（中间有空格）a为非负数，b为1或-1,当输入# #时，结束输入");
	            System.out.println("例如： \n3 1回车\n6 -1回车\n9 -1回车 \n8 -1回车 \n1 -1回车 \n0 0回车\n");
	            System.out.println("或者按照以下格式输入：3 1 6 -1 9 -1 8 -1 1 -1 2 1 0 0");
	            System.out.println("其中每两个数字一组，每组数字第一个数字代表下标，第二个数字1代表正字母，-1代表负字母");
	            while(true){       //当遇到两个# #是中止输入
	                int inKey=input.nextInt();
	                int inLetter=input.nextInt();
	                if(inKey=='#'&&inLetter=='#')break;
	                list.add(new Word(inKey,inLetter));
	            }
	        }
	        else{	 
	            //自动输入测试数据
	            list.add(new Word(7,1));
	            list.add(new Word(9,1));
	            list.add(new Word(4,-1));
	            list.add(new Word(1,-1));
	            list.add(new Word(10,-1));
	            list.add(new Word(11,1));
	            list.add(new Word(13,1));
	            list.add(new Word(7,-1));
	            list.add(new Word(1,-1));
	            list.add(new Word(15,-1));
	        }
	        input.close();
	        return list;
	    }
	    
	    
	    // 使用冒泡排序的思想生成半法式
	    private static ArrayList<Word> sort(ArrayList<Word> list) {
	        int length=list.size();           //获取输入字的长度
	        for(int i=0;i<length;i++){		  //冒泡排序两层循环
	            for(int j=0;j<length-i-1;j++){
	                Word word1=list.get(j);   //取第一个数据
	                Word word2=list.get(j+1); //取第二个数据
	                int key1=word1.getKey();  //取第一个数据的下角标
	                int key2=word2.getKey();  //取第二个数据的下角标
	              //左变量符号为正，右变量符号为负，不交换位置
	                if(word1.getLetter()==Word.POSITIVE
	                        &&word2.getLetter()==Word.NEGETIVE)
	                {
	                    
	                }
	              //左变量符号为负，右变量符号为正
	                else if(word1.getLetter()==Word.NEGETIVE       
	                		&&word2.getLetter()==Word.POSITIVE)  
	                  {
	                    //左变量下角标大于右变量下角标，交换位置，应用R2
	                    if(word1.getKey()<word2.getKey())
	                    {
	                    	list.get(j).setKey(key2);
	                        list.get(j).setLetter(Word.POSITIVE);
	                        list.get(j+1).setKey(key1+1);
	                        list.get(j+1).setLetter(Word.NEGETIVE);
	                        System.out.print("通过R2得到：");printList(list);
	                    }
	                    //左变量下角标小于右变量下角标，交换位置，应用R3
	                    else
	                    {
	                    	list.get(j).setKey(key2+1);
	                        list.get(j).setLetter(Word.POSITIVE);
	                        list.get(j+1).setKey(key1);
	                        list.get(j+1).setLetter(Word.NEGETIVE);
	                        System.out.print("通过R3得到：");printList(list);
	                    }
	 
	                   }
	              //左变量和右变量符号都为正
	                else if(word1.getLetter()==Word.POSITIVE
	                        &&word2.getLetter()==Word.POSITIVE)
	                {
	                    //左变量下角标大于右变量下角标，交换位置，应用R1
	                    if(word1.getKey()>word2.getKey()){
	                        list.get(j).setKey(key2);
	                        list.get(j+1).setKey(key1+1);
	                        System.out.print("通过R1得到：");printList(list);
	                    }
	                }
	              //左变量和右变量符号都为负
	                else{
	                    //左变量下角标小于右变量下角标，交换位置，应用R4
	                    if(word1.getKey()<word2.getKey()){
	                        list.get(j).setKey(key2+1);
	                        list.get(j+1).setKey(key1);
	                        System.out.print("通过R4得到：");printList(list);
	                    }
	                }
	            }
	        }
	        return list;
	 
	    }
	    
	    
	    //通过删除违规二元组，将半法式转化为法式
	    private static ArrayList<Word> subNormalToNormal(ArrayList<Word> list) {	        
	        int maxKeyIndex=getMaxKeyIndex(list);      //获取正变量中下角标最大的元素
	        int left=maxKeyIndex,right=maxKeyIndex+1;  //left指向正变量中下角标最大的元素，right指向负变量中下角标最大的元素
	        while(left>=0&&right<list.size()){         //left左移，right右移,left<0或者right>list.size()挑出循环
	            int pkey=list.get(left).getKey();      //获取left指向元素下角标
	            int rkey=list.get(right).getKey();     //获取right指向元素下角标
	            if(pkey<rkey){     //left指向元素下角标<right指向元素下角标
	                right++;	   //right右移
	            }
	            else if(pkey>rkey){ //left指向元素下角标>right指向元素下角标
	                left--;			//left左移
	            }
	            else{             //left指向元素下角标=right指向元素下角标
	                int nextPkey=list.get(left+1).getKey();    //比left指向元素下角标大的一个元素的下角标
	                int nextRkey=list.get(right-1).getKey();   //比right指向元素下角标大的一个元素下角标
	                if((nextPkey!=pkey+1)&&(nextRkey!=rkey+1)){ //不满足法式条件
	                    //已找到最大违规二元组，开始消除
	                    System.out.println("找到最大违规二元组：("+pkey+",1) 和 ("+rkey+",-1)");
	                    int cr1=0,cr4=0;          //记录使用R1和R4的次数
	                    if(left+1!=right)     //违规二元组中间有字母，与中间字母进行交换
	                    {        
	                        for(int l=left+1;l<=maxKeyIndex;l++)
	                        {
	                            list.get(l).keyMul();     //使用R1的逆运算
	                            //System.out.print("应用一次R1,得到：");
	                            //printList(list);
	                            cr1++;
	                        }
	                        for(int r=right-1;r>maxKeyIndex;r--)
	                        {
	                            list.get(r).keyMul();      //使用R4的逆运算
	                            //System.out.print("应用一次R4,得到：");
	                            //printList(list);
	                            cr4++;
	                        }
	                    }
	                    //删除变量，并修改左右指针
	                    list.remove(list.get(left));
	                    left--;right--;
	                    list.remove(list.get(right));
	                    System.out.print("使用"+cr1+"次R1,使用"+cr4+"次R4，使用一次R5，删除了最大违规二元组,得到：");
	                    printList(list);
	                }
	            }
	        }
	        return list;
	    }
	    //获取正变量中下角标最大的元素
	    private static int getMaxKeyIndex(ArrayList<Word> list) {
	        //因为已经是排列完成的半法式，
	        //所以，只需要找到两个正变量和负变量的交界即可
	        int i=0;
	        for(int j=0;j<list.size();j++){
	            if(list.get(j).getLetter()==1&&
	                    list.get(j+1).getLetter()==-1){
	                i=j;
	                break;
	            }
	        }
	        return i;
	    }
	    //打印表达式函数
	    private static void printList(ArrayList<Word> list) {
	       
	        for(int i=0;i<list.size();i++){
	            System.out.print(list.get(i).getKey()+" "+list.get(i).getLetter()  +"    ");
	        }
	 
	        System.out.println();
	    }
	 
	}
	 
	//x变量的结构
	class Word{
	    public static int POSITIVE=1;//正字母
	    public static int NEGETIVE=-1;//负字母
	    private int key;//下标
	    private int letter;//正负字母标识
	 
	    public Word(int key, int letter) {
	        this.key = key;
	        this.letter = letter;
	    }
	 
	    public Word() {
	    }
	    public int getKey() {
	        return key;
	    }
	    public void setKey(int key) {
	        this.key = key;
	    }
	    public int getLetter() {
	        return letter;
	    }
	    public void setLetter(int letter) {
	        this.letter = letter;
	    }
	    public void keyMul() {
	        this.key--;
	    }
	}