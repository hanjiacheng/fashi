package ��ʽ;
import java.util.ArrayList;
import java.util.Scanner; 
public class Main {
	    public static void main(String[] args) {
	        // ������w
	        ArrayList<Word> inputList=new ArrayList<Word>();//�洢������
	        ArrayList<Word> subNormalList=new ArrayList<Word>();//�洢�뷨ʽ
	        ArrayList<Word> normalList=new ArrayList<>();//�洢��ʽ
	        //����������
	        inputList=getInputList();
	        System.out.println("��������Ϊ��");
	        printList(inputList);
	        System.out.println("������ÿ�����ֵ�һ�����ִ����±꣬�ڶ�������1��������ĸ��-1������ĸ��");
	        //����NF1��Ҫ��������򣬵õ��뷨ʽ
	        subNormalList=sort(inputList);
	        System.out.println("�뷨ʽΪ��");
	        printList(subNormalList);
	        //ɾ��Υ���Ԫ��õ���ʽ
	        normalList=subNormalToNormal(subNormalList);
	        System.out.println("��ʽΪ��");
	        printList(normalList);
	    }
	    // ��Ϊ���������ֶ���������
	    private static ArrayList<Word> getInputList() {  
	        ArrayList<Word> list=new ArrayList<Word>();
	        Scanner input =new Scanner(System.in);
	        System.out.println("if you want to input word please input y/Y first,else input any key and enter to input data auto");
	        char c=input.next().charAt(0);
	        if(c=='Y'||c=='y'){
	            System.out.println("�밴���¸�ʽ�������ݣ�  a b �س������м��пո�aΪ�Ǹ�����bΪ1��-1,������# #ʱ����������");
	            System.out.println("���磺 \n3 1�س�\n6 -1�س�\n9 -1�س� \n8 -1�س� \n1 -1�س� \n0 0�س�\n");
	            System.out.println("���߰������¸�ʽ���룺3 1 6 -1 9 -1 8 -1 1 -1 2 1 0 0");
	            System.out.println("����ÿ��������һ�飬ÿ�����ֵ�һ�����ִ����±꣬�ڶ�������1��������ĸ��-1������ĸ");
	            while(true){       //����������# #����ֹ����
	                int inKey=input.nextInt();
	                int inLetter=input.nextInt();
	                if(inKey=='#'&&inLetter=='#')break;
	                list.add(new Word(inKey,inLetter));
	            }
	        }
	        else{	 
	            //�Զ������������
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
	    
	    
	    // ʹ��ð�������˼�����ɰ뷨ʽ
	    private static ArrayList<Word> sort(ArrayList<Word> list) {
	        int length=list.size();           //��ȡ�����ֵĳ���
	        for(int i=0;i<length;i++){		  //ð����������ѭ��
	            for(int j=0;j<length-i-1;j++){
	                Word word1=list.get(j);   //ȡ��һ������
	                Word word2=list.get(j+1); //ȡ�ڶ�������
	                int key1=word1.getKey();  //ȡ��һ�����ݵ��½Ǳ�
	                int key2=word2.getKey();  //ȡ�ڶ������ݵ��½Ǳ�
	              //���������Ϊ�����ұ�������Ϊ����������λ��
	                if(word1.getLetter()==Word.POSITIVE
	                        &&word2.getLetter()==Word.NEGETIVE)
	                {
	                    
	                }
	              //���������Ϊ�����ұ�������Ϊ��
	                else if(word1.getLetter()==Word.NEGETIVE       
	                		&&word2.getLetter()==Word.POSITIVE)  
	                  {
	                    //������½Ǳ�����ұ����½Ǳ꣬����λ�ã�Ӧ��R2
	                    if(word1.getKey()<word2.getKey())
	                    {
	                    	list.get(j).setKey(key2);
	                        list.get(j).setLetter(Word.POSITIVE);
	                        list.get(j+1).setKey(key1+1);
	                        list.get(j+1).setLetter(Word.NEGETIVE);
	                        System.out.print("ͨ��R2�õ���");printList(list);
	                    }
	                    //������½Ǳ�С���ұ����½Ǳ꣬����λ�ã�Ӧ��R3
	                    else
	                    {
	                    	list.get(j).setKey(key2+1);
	                        list.get(j).setLetter(Word.POSITIVE);
	                        list.get(j+1).setKey(key1);
	                        list.get(j+1).setLetter(Word.NEGETIVE);
	                        System.out.print("ͨ��R3�õ���");printList(list);
	                    }
	 
	                   }
	              //��������ұ������Ŷ�Ϊ��
	                else if(word1.getLetter()==Word.POSITIVE
	                        &&word2.getLetter()==Word.POSITIVE)
	                {
	                    //������½Ǳ�����ұ����½Ǳ꣬����λ�ã�Ӧ��R1
	                    if(word1.getKey()>word2.getKey()){
	                        list.get(j).setKey(key2);
	                        list.get(j+1).setKey(key1+1);
	                        System.out.print("ͨ��R1�õ���");printList(list);
	                    }
	                }
	              //��������ұ������Ŷ�Ϊ��
	                else{
	                    //������½Ǳ�С���ұ����½Ǳ꣬����λ�ã�Ӧ��R4
	                    if(word1.getKey()<word2.getKey()){
	                        list.get(j).setKey(key2+1);
	                        list.get(j+1).setKey(key1);
	                        System.out.print("ͨ��R4�õ���");printList(list);
	                    }
	                }
	            }
	        }
	        return list;
	 
	    }
	    
	    
	    //ͨ��ɾ��Υ���Ԫ�飬���뷨ʽת��Ϊ��ʽ
	    private static ArrayList<Word> subNormalToNormal(ArrayList<Word> list) {	        
	        int maxKeyIndex=getMaxKeyIndex(list);      //��ȡ���������½Ǳ�����Ԫ��
	        int left=maxKeyIndex,right=maxKeyIndex+1;  //leftָ�����������½Ǳ�����Ԫ�أ�rightָ�򸺱������½Ǳ�����Ԫ��
	        while(left>=0&&right<list.size()){         //left���ƣ�right����,left<0����right>list.size()����ѭ��
	            int pkey=list.get(left).getKey();      //��ȡleftָ��Ԫ���½Ǳ�
	            int rkey=list.get(right).getKey();     //��ȡrightָ��Ԫ���½Ǳ�
	            if(pkey<rkey){     //leftָ��Ԫ���½Ǳ�<rightָ��Ԫ���½Ǳ�
	                right++;	   //right����
	            }
	            else if(pkey>rkey){ //leftָ��Ԫ���½Ǳ�>rightָ��Ԫ���½Ǳ�
	                left--;			//left����
	            }
	            else{             //leftָ��Ԫ���½Ǳ�=rightָ��Ԫ���½Ǳ�
	                int nextPkey=list.get(left+1).getKey();    //��leftָ��Ԫ���½Ǳ���һ��Ԫ�ص��½Ǳ�
	                int nextRkey=list.get(right-1).getKey();   //��rightָ��Ԫ���½Ǳ���һ��Ԫ���½Ǳ�
	                if((nextPkey!=pkey+1)&&(nextRkey!=rkey+1)){ //�����㷨ʽ����
	                    //���ҵ����Υ���Ԫ�飬��ʼ����
	                    System.out.println("�ҵ����Υ���Ԫ�飺("+pkey+",1) �� ("+rkey+",-1)");
	                    int cr1=0,cr4=0;          //��¼ʹ��R1��R4�Ĵ���
	                    if(left+1!=right)     //Υ���Ԫ���м�����ĸ�����м���ĸ���н���
	                    {        
	                        for(int l=left+1;l<=maxKeyIndex;l++)
	                        {
	                            list.get(l).keyMul();     //ʹ��R1��������
	                            //System.out.print("Ӧ��һ��R1,�õ���");
	                            //printList(list);
	                            cr1++;
	                        }
	                        for(int r=right-1;r>maxKeyIndex;r--)
	                        {
	                            list.get(r).keyMul();      //ʹ��R4��������
	                            //System.out.print("Ӧ��һ��R4,�õ���");
	                            //printList(list);
	                            cr4++;
	                        }
	                    }
	                    //ɾ�����������޸�����ָ��
	                    list.remove(list.get(left));
	                    left--;right--;
	                    list.remove(list.get(right));
	                    System.out.print("ʹ��"+cr1+"��R1,ʹ��"+cr4+"��R4��ʹ��һ��R5��ɾ�������Υ���Ԫ��,�õ���");
	                    printList(list);
	                }
	            }
	        }
	        return list;
	    }
	    //��ȡ���������½Ǳ�����Ԫ��
	    private static int getMaxKeyIndex(ArrayList<Word> list) {
	        //��Ϊ�Ѿ���������ɵİ뷨ʽ��
	        //���ԣ�ֻ��Ҫ�ҵ������������͸������Ľ��缴��
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
	    //��ӡ���ʽ����
	    private static void printList(ArrayList<Word> list) {
	       
	        for(int i=0;i<list.size();i++){
	            System.out.print(list.get(i).getKey()+" "+list.get(i).getLetter()  +"    ");
	        }
	 
	        System.out.println();
	    }
	 
	}
	 
	//x�����Ľṹ
	class Word{
	    public static int POSITIVE=1;//����ĸ
	    public static int NEGETIVE=-1;//����ĸ
	    private int key;//�±�
	    private int letter;//������ĸ��ʶ
	 
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