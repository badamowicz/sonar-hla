import java.util.*;
import org.apache.commons.io.FileUtils;

/**
 * Script checks all generated files 'config.xml' by validating their XML.
 */

println "\n##################################################################################"
println ""
println "Starting Integration Test CheckAllProjects-IT."
println ""
println "##################################################################################"

try {
    
    File csvFile = null;
    boolean success = true;
    String csvData = null;
    
    csvFile = new File(basedir, "projects.csv");
    println "Trying to create CSV data to: " + csvFile.getAbsolutePath();
    
    if(csvFile == null) {
    	
    	println "No CSV file generated!";
    	success = false;
    	
    } else {
    
    	csvData = FileUtils.readFileToString(csvFile);
    	
    	if(csvData.isEmpty()) {
    	
    		println "No CSV data written to file!";
    		success = false;
        } else {
        
          println "Generated CSV data as expected!";
        }
    }

        
   

    println "\n##################################################################################"
    println ""
    println "Integration test CheckAllProjects-IT successful!\n"
    println ""
    println "##################################################################################"
 
    return success;
    
} catch(Exception e) {
    
    println "\n##################################################################################"
    println ""
    println "Integration test CheckAllProjects-IT failed:\n" + e.toString()
    println ""
    println "##################################################################################"
    
    return false;

}