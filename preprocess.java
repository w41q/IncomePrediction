import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class preprocess
{
	public static void main(String[] args)
	{
		switch(args[0])
		{
			case 0:
				// Remove records with missing value
				eliminateMissing();
				break;
			case 1:
				// Calculate value range in raw data
				range();
				break;
			case 2:
				// Descritize contineous value
				discretize();
				break;
			case 3:
				// Split raw data into features and values
				splitdata();
				break;
		}
	}
	
	public static void eliminateMissing()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("C:/15Spring/INF550/Homeworks/Project/adult/adult.test"));
			File file = new File("C:/15Spring/INF550/Homeworks/Project/adult/adult_no_missing.test");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			int count = 0, count_missing = 0;
			String sCurrentLine;
			long startTime = System.nanoTime();
			while ((sCurrentLine = br.readLine()) != null)
			{
				count++;
				if(sCurrentLine.contains("?"))
				{
					count_missing++;
					continue;
				}
				if(sCurrentLine == null || sCurrentLine.isEmpty())
				{
					System.out.println(count+"\nNULL!!!");
					break;
				}
				String fields[] = sCurrentLine.split(",");
				String line = "";
				for(int i=0;i<fields.length;i++)
				{
					line += fields[i].trim()+",";
				}
				line = line.substring(0, line.length()-1);
				bw.write(line+"\n");
			}

			if(bw!=null) bw.close();
			if (br != null)br.close();
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime)/1000000000+"s");
			System.out.println("records: " + count);
			System.out.println("missing: " + count_missing);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void range()
	{
		try
		{
			int fieldnum = 12;
			BufferedReader br = new BufferedReader(new FileReader("C:/15Spring/INF550/Homeworks/Project/adult/adult_no_missing.test"));
			File file = new File("C:/15Spring/INF550/Homeworks/Project/adult/"+fieldnum+".test");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			int count = 0;
			long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
			String sCurrentLine;
			long startTime = System.nanoTime();
			while ((sCurrentLine = br.readLine()) != null)
			{
				count++;
				if(sCurrentLine == null || sCurrentLine.isEmpty())
				{
					System.out.println(count+"\nNULL!!!");
					break;
				}
				String fields[] = sCurrentLine.split(",");
				bw.write(fields[fieldnum]+"\n");
				long value = Long.parseLong(fields[fieldnum]);
				if(value>max)
					max = value;
				if(value<min)
					min = value;
			}

			if(bw!=null) bw.close();
			if (br != null)br.close();
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime)/1000000000+"s");
			System.out.println("records: " + count);
			System.out.println("max: " + max);
			System.out.println("min: " + min);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void discretize()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("C:/15Spring/INF550/Homeworks/Project/adult/adult_no_missing.test"));
			File file = new File("C:/15Spring/INF550/Homeworks/Project/adult/discretize.test");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			int count = 0;
			String sCurrentLine;
			long startTime = System.nanoTime();
			while ((sCurrentLine = br.readLine()) != null)
			{
				count++;
				if(sCurrentLine == null || sCurrentLine.isEmpty())
				{
					System.out.println(count+"\nNULL!!!");
					break;
				}
				String fields[] = sCurrentLine.split(",");
				String value0 = "", value2 = "", value4 = "", value10 = "", value11 = "", value12 = "";
				
				value0 = "1" + (char)(Integer.parseInt(fields[0])/10+'a');
				
				long value = Long.parseLong(fields[2]);
				if(value<100000)
					value2 = "2a";
				else if(value>=100000&&value<200000)
					value2 = "2b";
				else if(value>=200000&&value<300000)
					value2 = "2c";
				else if(value>=300000&&value<400000)
					value2 = "2d";
				else if(value>=400000)
					value2 = "2e";
				
				value = Long.parseLong(fields[4]);
				value4 = "4" + (char)(value+'a');
				
				value = Long.parseLong(fields[10]);
				if(value==0)
					value10 = "10a";
				else if(value<10000)
					value10 = "10b";
				else if(value<20000)
					value10 = "10c";
				else
					value10 = "10d";
				
				value = Long.parseLong(fields[11]);
				if(value==0)
					value11 = "11a";
				else if(value<1800)
					value11 = "11b";
				else if(value<2000)
					value11 = "11c";
				else
					value11 = "11d";
				
				value = Long.parseLong(fields[12]);
				if(value<30)
					value12 = "12a";
				else if(value<40)
					value12 = "12b";
				else if(value<45)
					value12 = "12c";
				else if(value<50)
					value12 = "12d";
				else if(value<60)
					value12 = "12e";
				else
					value12 = "12f";
				
				String result = "";
				for(int i=0;i<fields.length;i++)
				{
					if(i==0)
						result += value0;
					else if(i==2)
						result += value2;
					else if(i==4)
						result += value4;
					else if(i==10)
						result += value10;
					else if(i==11)
						result += value11;
					else if(i==12)
						result += value12;
					else
						result += fields[i];
					result += ",";
				}
				
				bw.write(result.substring(0, result.length()-1)+"\n");
//				if(count==5)
//					break;
			}

			if(bw!=null) bw.close();
			if (br != null)br.close();
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime)/1000000000+"s");
			System.out.println("records: " + count);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void splitdata()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("C:/15Spring/INF550/Homeworks/Project/adult/discretize.test"));
			
			File filedata = new File("C:/15Spring/INF550/Homeworks/Project/adult/discretize_data.test");
			if (!filedata.exists())
			{
				filedata.createNewFile();
			}
			FileWriter fwdata = new FileWriter(filedata.getAbsoluteFile());
			BufferedWriter bwdata = new BufferedWriter(fwdata);
			
			File filelabel = new File("C:/15Spring/INF550/Homeworks/Project/adult/label.test");
			if (!filelabel.exists())
			{
				filelabel.createNewFile();
			}
			FileWriter fwlabel = new FileWriter(filelabel.getAbsoluteFile());
			BufferedWriter bwlabel = new BufferedWriter(fwlabel);
			
			int count = 0;
			String sCurrentLine;
			long startTime = System.nanoTime();
			while ((sCurrentLine = br.readLine()) != null)
			{
				count++;
				if(sCurrentLine == null || sCurrentLine.isEmpty())
				{
					System.out.println(count+"\nNULL!!!");
					break;
				}
				String fields[] = sCurrentLine.split(",");
				String linedata = "";
				for(int i=0;i<fields.length-1;i++)
				{
					linedata += fields[i]+",";
				}
				bwdata.write(linedata.substring(0, linedata.length()-1)+"\n");
				if(fields[fields.length-1].equals(">50K."))
					bwlabel.write("H\n");
				else
					bwlabel.write("L\n");
			}

			if(bwdata!=null) bwdata.close();
			if(bwlabel!=null) bwlabel.close();
			if (br != null)br.close();
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime)/1000000000+"s");
			System.out.println("records: " + count);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void splitdata_dt()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("C:/15Spring/INF550/Homeworks/Project/adult/decisiontree/adult_no_missing.test"));
			
			File filedata = new File("C:/15Spring/INF550/Homeworks/Project/adult/decisiontree/dt_data.test");
			if (!filedata.exists())
			{
				filedata.createNewFile();
			}
			FileWriter fwdata = new FileWriter(filedata.getAbsoluteFile());
			BufferedWriter bwdata = new BufferedWriter(fwdata);
			
			File filelabel = new File("C:/15Spring/INF550/Homeworks/Project/adult/decisiontree/dt_label.test");
			if (!filelabel.exists())
			{
				filelabel.createNewFile();
			}
			FileWriter fwlabel = new FileWriter(filelabel.getAbsoluteFile());
			BufferedWriter bwlabel = new BufferedWriter(fwlabel);
			
			int count = 0;
			String sCurrentLine;
			long startTime = System.nanoTime();
			while ((sCurrentLine = br.readLine()) != null)
			{
				count++;
				if(sCurrentLine == null || sCurrentLine.isEmpty())
				{
					System.out.println(count+"\nNULL!!!");
					break;
				}
				String fields[] = sCurrentLine.split(",");
				String linedata = "";
				for(int i=0;i<fields.length-1;i++)
				{
					linedata += fields[i]+",";
				}
				bwdata.write(linedata.substring(0, linedata.length()-1)+"\n");
				if(fields[fields.length-1].equals(">50K."))
					bwlabel.write("H\n");
				else
					bwlabel.write("L\n");
			}

			if(bwdata!=null) bwdata.close();
			if(bwlabel!=null) bwlabel.close();
			if (br != null)br.close();
			long endTime = System.nanoTime();
			System.out.println((endTime-startTime)/1000000000+"s");
			System.out.println("records: " + count);
			System.out.println("Done");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
