package uno;



/**
 * Created by jkao on 3/8/16.
 */
public class ImgPathResolver {

    public static final String imgDirName = "image/";

    public static String resolve(Card card){


        if (card.getType().equals("Change Color")) return imgDirName + "c4_00.png";
        if (card.getType().equals("Draw 4")) return imgDirName + "c4_01.png";

        StringBuilder sb = new StringBuilder();

        sb.append(imgDirName);
        sb.append("c");
        sb.append(resolveColor(card.getColor()));
        sb.append("_");
        sb.append(resolveType(card.getType(),card.getValue()));
        sb.append(".png");


        return sb.toString();
    }

    private static String resolveColor(String color){

        String str = null;

        switch (color){
            case "Yellow": str = "1";
                break;
            case "Red": str = "0";
                break;
            case "Blue": str = "3";
                break;
            case "Green": str = "2";
        }
        return str;
    }

    private static String resolveType(String type,int value){
        

        String str = null;
        if(type.equals("Number") && value==0)
        {
         str="0"+value;
        }
         if(type.equals("Number")  && value==1)
        {
         str="0"+value;
        }
          if(type.equals("Number")  && value==2)
        {
         str="0"+value;
        }
           if(type.equals("Number")  && value==3)
        {
         str="0"+value;         
        }
            if(type.equals("Number")  && value==4)
        {
         str="0"+value;
        }
             if(type.equals("Number")  && value==5)
        {
         str="0"+value;
        }
              if(type.equals("Number")  && value==6)
        {
         str="0"+value;
        }
               if(type.equals("Number")  && value==7)
        {
         str="0"+value;
        }
                if(type.equals("Number")  && value==8)
        {
         str="0"+value;
        }
                 if(type.equals("Number")  && value==9)
        {
         str="0"+value;
        }
                  if(type.equals("Skip")  && value==20){
         str="10";
        }
                  if(type.equals("Reverse")  && value==20)
        {
         str="11";
        }
                   if(type.equals("Draw 2")  && value==20)
        {
         str="12";
        }

        return str;
    }

}
