/**
 * Created by 小陈 on 2017/5/25.
 */
/**
 * 解析搜狗词库文件
 **/
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

public class ParseSogo {
    public static void main(String[] args)throws Exception {

        sogou("D:\\学习资料（知识图谱）\\数据集\\sougouxibaoCNGR\\89个节日.scel","D:\\学习资料（知识图谱）\\数据集\\sougouxibaoCNGR\\89个节日.txt",true);
    }

    /**
     * 读取scel的词库文件
     * 生成txt格式的文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     * @param isAppend  是否拼接追加词库内容 true 代表追加,false代表重建
     *
     * **/
    private static void sogou(String inputPath,String outputPath,boolean isAppend) throws IOException{
        File file=new File(inputPath);
        if(!isAppend){
            if(Files.exists(Paths.get(outputPath),LinkOption.values())){
                System.out.println("存储此文件已经删除");
                Files.deleteIfExists(Paths.get(outputPath));

            }
        }
        RandomAccessFile raf=new RandomAccessFile(outputPath, "rw");

        int count=0;
        SougouScelMdel model = new SougouScelReader().read(file);
        Map<String,List<String>> words = model.getWordMap(); //词<拼音,词>
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
                raf.write((word+"\n").getBytes());//写入txt文件
                count++;


            }
        }
        raf.close();
        System.out.println("生成txt成功！,总计写入: "+count+" 条数据！");
    }

}
