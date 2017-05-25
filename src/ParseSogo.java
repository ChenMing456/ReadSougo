/**
 * Created by С�� on 2017/5/25.
 */
/**
 * �����ѹ��ʿ��ļ�
 **/
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

public class ParseSogo {
    public static void main(String[] args)throws Exception {

        sogou("D:\\ѧϰ���ϣ�֪ʶͼ�ף�\\���ݼ�\\sougouxibaoCNGR\\89������.scel","D:\\ѧϰ���ϣ�֪ʶͼ�ף�\\���ݼ�\\sougouxibaoCNGR\\89������.txt",true);
    }

    /**
     * ��ȡscel�Ĵʿ��ļ�
     * ����txt��ʽ���ļ�
     * @param inputPath ����·��
     * @param outputPath ���·��
     * @param isAppend  �Ƿ�ƴ��׷�Ӵʿ����� true ����׷��,false�����ؽ�
     *
     * **/
    private static void sogou(String inputPath,String outputPath,boolean isAppend) throws IOException{
        File file=new File(inputPath);
        if(!isAppend){
            if(Files.exists(Paths.get(outputPath),LinkOption.values())){
                System.out.println("�洢���ļ��Ѿ�ɾ��");
                Files.deleteIfExists(Paths.get(outputPath));

            }
        }
        RandomAccessFile raf=new RandomAccessFile(outputPath, "rw");

        int count=0;
        SougouScelMdel model = new SougouScelReader().read(file);
        Map<String,List<String>> words = model.getWordMap(); //��<ƴ��,��>
        Set<Entry<String,List<String>>> set = words.entrySet();
        Iterator<Entry<String,List<String>>> iter = set.iterator();
        while(iter.hasNext()){
            Entry<String,List<String>> entry = iter.next();
            List<String> list = entry.getValue();
            int size = list.size();
            for(int i = 0; i < size; i++){
                String word = list.get(i);

                //System.out.println(word);
                raf.seek(raf.getFilePointer());
                raf.write((word+"\n").getBytes());//д��txt�ļ�
                count++;


            }
        }
        raf.close();
        System.out.println("����txt�ɹ���,�ܼ�д��: "+count+" �����ݣ�");
    }

}
