package com.goralsoft.codegen.javawebapp;

/**
 * kabadi - codegen tool makes simpler the development of Enterprise Apps
 * 
 */
 /*
 Test for editing
 */
import java.io.FileWriter;
import java.io.Writer;
import com.goralsoft.codegen.Util.AllColumns;
import com.goralsoft.codegen.cgs.CodeGenFactory;
import com.goralsoft.codegen.cgs.CodeGenMetaData;
import com.goralsoft.codegen.cgs.CodeGenWebEnv4Java;
import com.goralsoft.codegen.cgs.CodegenTask;
import com.goralsoft.codegen.cgs.Table;
import com.goralsoft.codegen.cgs.defaultui.JqueryTemplate;
import com.goralsoft.codegen.igs.ICodeGenMetaData;
import com.goralsoft.codegen.igs.IWebEnv;
import com.goralsoft.codegen.igs.IWebPage;
import com.goralsoft.codegen.igs.IWebUITemplate;
public class App 
{
    public static void main( String[] args )
    {    	
        System.out.println( "Generating Code...Please wait!" );
        App obj=new App();
        obj.execute();		
        System.out.println( "Codegeneration completed" );
    }
    public void execute()
    {
    	try
    	{
    	/*
    	 * Environment for codegeneration
    	 * Ex: UI=Jquery,DB=SQLSERVER/ORACLE/SYBASE/MYSQL,Language=C#/JAVA/PHP
    	 */
    	IWebEnv env = new CodeGenWebEnv4Java(IWebEnv.UI.HTML5, IWebEnv.DB.SQLSERVER, IWebEnv.LANG.JAVA);
        /*
         * 
         */
        CodeGenFactory cgf = new CodeGenFactory();
        ICodeGenMetaData cgmd = new CodeGenMetaData();
        AllColumns ciareqcols = new AllColumns(cgf);
        ciareqcols.addCol("examperiod", "string", 20, 0,"Exam Period").
                addCol("papercode", "string", 50, 0,"Paper Code").
                addCol("dept", "string", 20, 0,"DepartMent").
                addCol("fees", "decimal", 4, 0,"Fees").
                addCol("partno", "int", 4, 0,"PartNo").
                addCol("creditval", "int", 4, 0,"Credit Value").
                addCol("totalminmark", "decimal", 4, 0,"TotalMinMark").
                addCol("papertitle", "string", 20, 0,"Paper Title");

        Table ciareq = cgf.createNewTable("ciarequired", ciareqcols.getAllCols());
        
		ciareq.addPrimaryKey("examperiod");
		ciareq.addPrimaryKey("papercode");
		
		/* Add more tables here*/
		
		//cgmd.reIndex();
        cgf.setGetCodegenMetaData(cgmd);
        
        Writer out = new FileWriter("E:/VASU/codegen/" + ciareq.getName() + ".html");
        IWebUITemplate uit=new JqueryTemplate(IWebUITemplate.TemplateType.ENTRYLIST,out,env);
        IWebPage page=uit.getPage(ciareq);
        
        out=uit.renderForSingleTableUI(page);
        out.close();
        
        CodegenTask cgt=new CodegenTask(IWebUITemplate.TemplateType.ENTRYLIST, env, "E:/VASU/codegen/",cgmd);
        cgt.renderForSingleTableTask(ciareq);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
