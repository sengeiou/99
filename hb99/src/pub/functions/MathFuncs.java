package pub.functions;

import java.util.Date;
import java.util.Random;
 
public class MathFuncs {

	private static Random random = new Random();

	public static int randInt() {
		return random.nextInt();
	}
	
	//取1-max中的随机数
	public static int randInt(int max){
		int num = (int) (Math.random()*max)+1;
		return num;
	}
	
	//5位随机数
	public static int randomInt(){
		return (int)Math.round(Math.random()*(99999-10000)+10000);
	}
	
	public static void main(String[] args) {
		int ii = (int)Math.round(Math.random()*(9999-1000)+1000);
		System.out.println("ii="+ii);
		System.out.println("code="+createVerificationCode());
	}
	
	//生成的12位随机数验证码
	public static String createVerificationCode(){
		int ii = (int)Math.round(Math.random()*(9999-1000)+1000);//4位随机数
		String code = "";
		code = DateFuncs.toString(new Date(), "ddHHmmss")+ii;
		return code;
	}

}
