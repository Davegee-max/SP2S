package SerialPort;

import Data.EKGDTO;
import com.sun.xml.internal.bind.v2.TODO;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EKGSensor {
    String[] ports = SerialPortList.getPortNames();
    SerialPort serialPort;
    private String result = null;
    private String bufferstring = "";
    private List<EKGDTO> guiList =new LinkedList<>();

    public EKGSensor() {
        System.out.println(ports[0]);
        System.out.println("EKG SENSOR INITIERET");

        try {
            serialPort = new SerialPort(ports[0]);
            serialPort.openPort();
            serialPort.setRTS(true);
            serialPort.setDTR(true);
            serialPort.setParams(9600, 8, 1, serialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (SerialPortException e) {
            e.printStackTrace();

        }
    }
    public List<EKGDTO> hentData() {

        try {
            if(serialPort.getInputBufferBytesCount() > 12) {
                result = serialPort.readString();
                bufferstring += result;

                int i = bufferstring.lastIndexOf(' ');
                String parseString = bufferstring.substring(0,i).trim();
                bufferstring = bufferstring.substring(i);

               //System.out.println(parseString);

                String[] inputValues;

                if (parseString != null && parseString.charAt(parseString.length() - 1) == ' ') ;
                parseString = parseString.substring(0, parseString.length() - 1);
                inputValues = parseString.split(" ");
                List<EKGDTO> ekgdtos = new LinkedList<>();
                for (int h = 0; h < inputValues.length; h++) {
                    if (!Objects.equals(inputValues[h],"")) {
                       EKGDTO ekgdto = new EKGDTO();
                       ekgdto.setEKG(Integer.parseInt(inputValues[h]));
                       ekgdtos.add(ekgdto);

                        //System.out.println(ekgdtos.get(h).getEKG());

                        /*
                        if (Values[h] != null && !Values[h].equals("")) {
                            EKGDTO ekgdto = new EKGDTO();
                            ekgdto.setEKG(Double.parseDouble(Values[h]));
                            values.add(ekgdto);
                            System.out.println(Arrays.toString(Values));
                         */
                   }

                }
                guiList.addAll(ekgdtos);
                if(guiList.size()>100){
                    //TODO print i gui
                    for (int k=0; k<guiList.size() ; k++)
                    {
                        System.out.println(guiList.get(k).getEKG());
                        guiList.clear();
                    }
                 // System.out.println(Arrays.deepToString(guiList.toArray()));
                    //guiList.forEach(System.out::println);
                    //System.out.println(guiList.get(i).getEKG());
                    /*for (int k = 0; k < guiList.size(); k++) {
                        System.out.println(guiList.get(k).getEKG());
                        guiList.clear();
                    }
*/

                }
                return guiList;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return null;
    }
}
/*System.out.println(result);
                        EKGDTO ekgdto = new EKGDTO();
                        ekgdto.setEKG(Double.parseDouble(Values[i]));
                        ekgdto.setTime(new Timestamp(System.currentTimeMillis()));
                        values.add(ekgdto);
                        System.out.println(ekgdto);
                        Thread.sleep(1);
    */