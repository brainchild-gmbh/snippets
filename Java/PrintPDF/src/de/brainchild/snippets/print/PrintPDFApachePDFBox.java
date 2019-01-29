package de.brainchild.snippets.print;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;

/**
 * Prints a PDF document using Apache PDFBox.
 * 
 * @author Mike Werner - <a href="http://www.brain-child.de">brainchild GmbH</a>
 */
public class PrintPDFApachePDFBox {

    public static void main(String args[]) throws PrinterException, InvalidPasswordException, IOException {

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (!printerJob.printDialog()) {
            System.out.println("Printing aborted by user.");
            return;
        }

        PrintService printService = printerJob.getPrintService();
        
        try (PDDocument document = PDDocument.load(new File(args[0]))) {

            printerJob.setPageable(new PDFPageable(document));
            printerJob.setPrintService(printService);
            

            System.out.println(String.format("Printing %s ...", args[0]));

            printerJob.print();

            System.out.println("Document sucessfully printed.");
        }
    }
}
