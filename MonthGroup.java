import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//	       YearMMDD	hr Temp
//690190 13910 20060201_0 51.75 33.0 24 1006.3 24 943.9 24 15.0 24 10.7 24 22.0 28.9 0.00I 999.9 000000
public class MonthGroup {
// First mapper class
// Input: input text file
// Output key: StationId+Year+Month+Section, Output value: Temperature
 
public static String classify(int hr){
        	String result = null;
        	if(hr>4 && hr<=10)
        		result = "5-11";
        	if(hr>10&&hr<=16)
        		result = "11-17";
        	if(hr>16&&hr<=22)
        		result = "17-23";
        	if(hr>22&&hr<=4)
        		result = "23-5";
   		return result;
        }
// First mapper class
// Input: input text file
// Output key: StationId+Year+Month, Output value: Temperature+Wind
public static class Map1 extends Mapper<LongWritable, Text, Text, Text> {
public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
String inputLine = value.toString();
if (inputLine.substring(0, 1).matches("[0-9]")) {
String arr[] = inputLine.split("[ ,]+");
String hour;
if(arr[2].length()>10)
	hour= arr[2].substring(9, 11);
else
	hour= arr[2].substring(9, 10);
String section = classify(Integer.parseInt(hour));
if((Integer.parseInt(arr[2].substring(4, 6))==01 )||(Integer.parseInt(arr[2].substring(4, 6))==02 ) ) {
String keyToReducer = arr[0]+arr[2].substring(4, 6) ;
String month = arr[2].substring(4, 6);
double temperature = Double.parseDouble(arr[3].toString());
double dewpoint = Double.parseDouble(arr[4].toString());
double wind = Double.parseDouble(arr[12].toString());
context.write(new Text(keyToReducer), new Text(temperature+ "," + dewpoint+ "," + wind+ "," + hour+","+month));
}
}
}
}
// First reducer class
// Input key: StationId+Year+Month, Input value: Temperature+Wind
// Output key: StationId+Year+Month, Output value: minTemperature+maxTemperature+minWind+MaxWind
public static class Reduce1 extends Reducer<Text, Text, Text, Text> {
public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
int tempValuesCount = 0;
double sumTemp = 0.0;
double maxTemp = 0.0;
double minTemp = 1000.0;
int dewValuesCount = 0;
double sumDew = 0.0;
double maxDew = 0.0;
double minDew = 1000.0;
int windValuesCount = 0;
double sumWind = 0.0;
double maxWind = 0.0;
double minWind = 1000.0;
double tempMissingValue = 9999.9;
double tempMinimumValue = 0.0;
double dewMissingValue = 9999.9;
double dewMinimumValue = 0.0;
double windMissingValue = 999.9;

int tempValuesCount1 = 0;
double sumTemp1 = 0.0;
double maxTemp1 = 0.0;
double minTemp1 = 1000.0;
int dewValuesCount1 = 0;
double sumDew1 = 0.0;
double maxDew1 = 0.0;
double minDew1 = 1000.0;
int windValuesCount1 = 0;
double sumWind1 = 0.0;
double maxWind1 = 0.0;
double minWind1 = 1000.0;
double tempMissingValue1 = 9999.9;
double tempMinimumValue1 = 0.0;
double dewMissingValue1 = 9999.9;
double dewMinimumValue1 = 0.0;
double windMissingValue1 = 999.9;

int tempValuesCount2 = 0;
double sumTemp2 = 0.0;
double maxTemp2 = 0.0;
double minTemp2 = 1000.0;
int dewValuesCount2 = 0;
double sumDew2 = 0.0;
double maxDew2 = 0.0;
double minDew2 = 1000.0;
int windValuesCount2 = 0;
double sumWind2 = 0.0;
double maxWind2 = 0.0;
double minWind2 = 1000.0;
double tempMissingValue2 = 9999.9;
double tempMinimumValue2 = 0.0;
double dewMissingValue2 = 9999.9;
double dewMinimumValue2 = 0.0;
double windMissingValue2 = 999.9;

int tempValuesCount3 = 0;
double sumTemp3 = 0.0;
double maxTemp3 = 0.0;
double minTemp3 = 1000.0;
int dewValuesCount3 = 0;
double sumDew3 = 0.0;
double maxDew3 = 0.0;
double minDew3 = 1000.0;
int windValuesCount3 = 0;
double sumWind3 = 0.0;
double maxWind3 = 0.0;
double minWind3 = 1000.0;
double tempMissingValue3 = 9999.9;
double tempMinimumValue3 = 0.0;
double dewMissingValue3 = 9999.9;
double dewMinimumValue3 = 0.0;
double windMissingValue3 = 999.9;
double month=0.0;
for (Text val : values) {
String tempwind[] = val.toString().split(",");
 month = Double.parseDouble(tempwind[4].toString());
double temp = Double.parseDouble(tempwind[0].toString());
double dew = Double.parseDouble(tempwind[1].toString());
double wind = Double.parseDouble(tempwind[2].toString());
double temp1 = Double.parseDouble(tempwind[0].toString());
double dew1 = Double.parseDouble(tempwind[1].toString());
double wind1 = Double.parseDouble(tempwind[2].toString());
double temp2 = Double.parseDouble(tempwind[0].toString());
double dew2 = Double.parseDouble(tempwind[1].toString());
double wind2 = Double.parseDouble(tempwind[2].toString());
double temp3 = Double.parseDouble(tempwind[0].toString());
double dew3 = Double.parseDouble(tempwind[1].toString());
double wind3 = Double.parseDouble(tempwind[2].toString());
String section = classify(Integer.parseInt(tempwind[3].toString()));
if(section=="5-11" ){
if (temp != tempMissingValue && temp >= tempMinimumValue) {
if (temp < minTemp)
minTemp = temp;
if (temp > maxTemp)
maxTemp = temp;
sumTemp += temp;
tempValuesCount++;
}
if (dew != dewMissingValue && dew >= dewMinimumValue) {
if (dew < minDew)
minDew = temp;
if (dew > maxDew)
maxDew = temp;
sumDew += temp;
dewValuesCount++;
}
if (wind != windMissingValue) {
if (wind < minWind)
minWind = wind;
if (wind > maxWind)
maxWind = wind;
sumWind += wind;
windValuesCount++;
}
}
section = classify(Integer.parseInt(tempwind[3].toString()));
if(section=="11-17" ){
if (temp1 != tempMissingValue1 && temp1 >= tempMinimumValue1) {
if (temp1 < minTemp1)
minTemp1 = temp1;
if (temp1 > maxTemp1)
maxTemp1 = temp1;
sumTemp1 += temp1;
tempValuesCount1++;
}
if (dew1 != dewMissingValue1 && dew1 >= dewMinimumValue1) {
if (dew1 < minDew1)
minDew1 = temp1;
if (dew1 > maxDew1)
maxDew1 = temp1;
sumDew1 += temp1;
dewValuesCount1++;
}
if (wind1 != windMissingValue1) {
if (wind1 < minWind1)
minWind1 = wind1;
if (wind1 > maxWind1)
maxWind1 = wind1;
sumWind1 += wind1;
windValuesCount1++;
}
}

if(section=="17-23"){
if (temp2 != tempMissingValue2 && temp2 >= tempMinimumValue2) {
if (temp2 < minTemp2)
minTemp2 = temp2;
if (temp2 > maxTemp2)
maxTemp2 = temp2;
sumTemp2 += temp2;
tempValuesCount2++;
}
if (dew2 != dewMissingValue2 && dew2 >= dewMinimumValue2) {
if (dew2 < minDew2)
minDew2 = temp2;
if (dew2 > maxDew2)
maxDew2 = temp2;
sumDew2 += temp2;
dewValuesCount2++;
}
if (wind2 != windMissingValue2) {
if (wind2 < minWind2)
minWind2 = wind2;
if (wind2 > maxWind2)
maxWind2 = wind2;
sumWind2 += wind2;
windValuesCount2++;
}
}
if((Integer.parseInt(tempwind[3].toString())>22) || (Integer.parseInt(tempwind[3].toString())<=4)){
if (temp3 != tempMissingValue3 && temp3 >= tempMinimumValue3) {
if (temp3 < minTemp3)
minTemp3 = temp3;
if (temp3 > maxTemp3)
maxTemp3 = temp3;
sumTemp3 += temp3;
tempValuesCount3++;
}
if (dew3 != dewMissingValue3 && dew3 >= dewMinimumValue3) {
if (dew3 < minDew3)
minDew3 = temp3;
if (dew3 > maxDew3)
maxDew3 = temp3;
sumDew3 += temp3;
dewValuesCount3++;
}
if (wind3 != windMissingValue3) {
if (wind3 < minWind3)
minWind3 = wind3;
if (wind3 > maxWind3)
maxWind3 = wind3;
sumWind3 += wind3;
windValuesCount3++;
}
}
}

context.write(key, new Text( "Month = " + month +"  "+String.format("%.2f", (sumTemp/tempValuesCount))+"|" + String.format("%.2f", (sumDew/dewValuesCount))+"|" + String.format("%.2f", (sumWind/windValuesCount))+"|" + String.format("%.2f", (sumTemp1/tempValuesCount1))+"|" + String.format("%.2f", (sumDew1/dewValuesCount1))+"|" + String.format("%.2f", (sumWind1/windValuesCount1))+"|" + String.format("%.2f", (sumTemp2/tempValuesCount2))+"|" + String.format("%.2f", (sumDew2/dewValuesCount2))+"|" + String.format("%.2f", (sumWind2/windValuesCount2))+"|" + String.format("%.2f", (sumTemp3/tempValuesCount3))+"|" + String.format("%.2f", (sumDew3/dewValuesCount3))+"|" + String.format("%.2f", (sumWind3/windValuesCount3))));
}
}
public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/hdfs-site.xml"));
@SuppressWarnings("deprecation")
Job job1 = new Job(conf, "firstJob");
job1.setJarByClass(MonthGroup.class);
job1.setOutputKeyClass(Text.class);
job1.setOutputValueClass(Text.class);
job1.setMapperClass(Map1.class);
job1.setReducerClass(Reduce1.class);
job1.setInputFormatClass(TextInputFormat.class);
job1.setOutputFormatClass(TextOutputFormat.class);
FileInputFormat.addInputPath(job1, new Path(args[0]));
FileOutputFormat.setOutputPath(job1, new Path(args[1]));
job1.waitForCompletion(true);
}

}
