package lab_3;
import java.util.Scanner;  
import java.lang.Math;

public class Subnetting {
   
    //Returns the required no. of combinations
    public static double combinations(int n)
    {
        return (Math.pow(2, n)-1);
    }

    //Returns bits required if subnets are given to function
    public static int bitsRequired(int subnets)
    {
        String binary = Integer.toBinaryString(subnets);
        return (binary.length());
       
    }

    //Returns first part of IP address to check class of IP address
    public static String ipRange(String ipAdd)
    {
        String ipRan = "";
        for(int i = 0; i<ipAdd.length(); i++)
        {
            if(ipAdd.charAt(i) == '.')
                break;
            else{
                ipRan += ipAdd.charAt(i);
            }
        }
        return ipRan;
    }

    //Class Finder --> Retruns the class of given ip address
    public static String classFinder(String ipRan)
    {
        if(Integer.parseInt(ipRan) >= 0 && Integer.parseInt(ipRan) <= 127)
        {
            return "Class A";
        }
        else if(Integer.parseInt(ipRan) >= 128 && Integer.parseInt(ipRan) <= 191){
            return "Class B";
        }
        else if(Integer.parseInt(ipRan) >= 192 && Integer.parseInt(ipRan) <= 223){
            return "Class C";
        }
        else
        {
            return "NULL";
        }
    }

    //Return gap between two subnets required for class A and B
    public static double gapBetweenTwoSubnets(int subnets)
    {
        return (Math.pow(2, (8- bitsRequired(subnets)))-1);
    }

    //Find Subnet Mask
    public static double subnetMask(int requiredNBits)
    {
        int n = 8 - requiredNBits;
        return (256- (Math.pow(2, n)));
    }

    //Main function to find subnets
    public static void subnetsIP(String ip, String cls, int bits, int subnets)
    {
        int subnetStart = -1;
        for(int i = 0; i<subnets; i++)
        {
            System.out.println("Subnet No. " + (i+1));
            
            if(cls == "Class A")
            {
                if(subnetStart <=255){
                String[] splitedIP = ip.split("[.]");
                // System.out.println(splitedIP[0]);
                String subnetStart1 = splitedIP[0]+"."+ (int)(subnetStart+1) + "." +"0" + "." + "0";
                System.out.println("New subnet IP: " + subnetStart1);
                // int remainingBit = 8 - bits;
                double diff = gapBetweenTwoSubnets(subnets);
                subnetStart = (subnetStart+1) + (int)diff;
                String subnetEnd1 = splitedIP[0] + "." +(int)(subnetStart)+"."+"0"+ "." +"0";
                System.out.println("New subnet IP: " + subnetEnd1);
                }
            }
            else if(cls == "Class B")
            {
                if(subnetStart <= 255){
                    String[] splitedIP = ip.split("[.]");
                    // System.out.println(splitedIP[0]);
                    String subnetStart1 = splitedIP[0]+"."+splitedIP[1]+"." + +(int)(subnetStart+1) + "." +"0";
                    System.out.println("New subnet IP: " + subnetStart1);
                    // int remainingBit = 8 - bits;
                    double diff = gapBetweenTwoSubnets(subnets);
                    subnetStart = (subnetStart+1) + (int)diff;
                    String subnetEnd1 = splitedIP[0]+"."+splitedIP[1]+"." + +(int)(subnetStart) + "." +"0";
                
                    System.out.println("New subnet IP: " + subnetEnd1);
                }
                
            }
            else if(cls == "Class C")
            {
                if(subnetStart <= 255){
                String[] splitedIP = ip.split("[.]");
                // System.out.println(splitedIP[0]);
                String threeBit = splitedIP[0]+"."+splitedIP[1]+"."+splitedIP[2]+".";
                System.out.println("New subnet IP: " + threeBit + (subnetStart+1));
                int remainingBit = 8 - bits;
                double diff = combinations(remainingBit);
                subnetStart = (subnetStart+1) + (int) diff;
                System.out.println("New subnet IP: " + threeBit+ (subnetStart));
                }
            }
        }
    }

    public static void main(String[] args) {
        String classAdefSub = "255.0.0.0";
        String classBdefSub = "255.255.0.0";
        String classCdefSub = "255.255.255.0";
        System.out.println("Enter Your Smaple ip address: ");
       
        Scanner input = new Scanner(System.in);
        String ip = input.nextLine();
       
        System.out.println("Number of subnets you want: ");
        int subnets = input.nextInt();
       
       
        String[] splitedIP = ip.split("[.]");
        String ipRan = ipRange(ip); 
        String cls = classFinder(ipRan);
        String threeBit = "255"+"."+ "255" +"."+"255"+".";
        String twoBits = "255"+"."+ "255"+"."+(int)subnetMask(bitsRequired(subnets));
        String oneBits = "255"+ "." + (int)subnetMask(bitsRequired(subnets)) + "." + "0" + "." + "0";


        System.out.println("Given IP belongs to: "+cls);
        if(cls == "Class A")
        {
            System.out.println("Default Subnet mask is: " + classAdefSub);
            System.out.println("Subnet Mask for Class A for ip " + ip + " With Subnets: "+ subnets+ " is: "+oneBits);
        }else if(cls == "Class B")
        {
            System.out.println("Default Subnet mask is: " + classBdefSub);
            System.out.println("Subnet Mask for Class B for ip " + ip + " With Subnets: "+ subnets+" is: " +twoBits + "." + "0");
        }
        else if (cls == "Class C")
        {
            System.out.println("Default Subnet mask is: " + classCdefSub);
            System.out.println("Subnet Mask for Class C for ip " + ip + " With Subnets: "+ subnets +" is: " +threeBit +(int)subnetMask(bitsRequired(subnets)));
        }
        int bits = bitsRequired(subnets);
        System.out.println("Number of bits required: "+bits);
        subnetsIP(ip, cls, bits, subnets);

    }
}
