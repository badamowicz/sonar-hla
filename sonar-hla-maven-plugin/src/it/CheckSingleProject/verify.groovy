import java.util.*;
import org.apache.commons.io.FileUtils;

/**
 * Script checks all generated files 'config.xml' by validating their XML.
 */

println "\n##################################################################################"
println ""
println "Starting Integration Test CheckSingleProject-IT."
println ""
println "##################################################################################"

try {
    
    File csvFile = null;
    boolean success = true;
    List<String> csvData = null;
    
    csvFile = new File(basedir, "projects.csv");
	csvData = FileUtils.readLines(csvFile);
	
	if(csvData.size() != 2) {
	
		println "Only two lines of CSV data expected, but found " + csvData.size() + " !";
		success = false;
	} else {
	
	  println "Generated single project CSV data as expected!";
	}

        
   

    println "\n##################################################################################"
    println ""
    println "Integration test CheckSingleProject-IT successful!\n"
    println ""
    println "##################################################################################"
 
    return success;
    
} catch(Exception e) {
    
    println "\n##################################################################################"
    println ""
    println "Integration test CheckSingleProject-IT failed:\n" + e.toString()
    println ""
    println "##################################################################################"
    
    return false;

}
