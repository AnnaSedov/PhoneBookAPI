package helpers;

import java.util.Random;

public class PhoneNumberGenerator {

//    public static void main(String[] args){
//        System.out.println("Number: "+generatePhoneNumber());
//    }   //проверка генерации
    private static final int MinLength=10;
    private static final int MaxLength=15;
    public static String generatePhoneNumber(){
        Random random=new Random();
        int length=random.nextInt(MaxLength-MinLength-1)+MinLength;
        StringBuilder phoneNumber=new StringBuilder();
        for(int i=0;i<length;i++){
            if(i==0){
                phoneNumber.append(random.nextInt(7)+2);


            }else{phoneNumber.append(random.nextInt(10));
            }
        }
        return phoneNumber.toString();
    }
}
