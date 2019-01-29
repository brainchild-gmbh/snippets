package de.brainchild.snippets.print;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

/**
 * Prints a PDF document directly using {@link SimpleDoc}. This actually sends the
 * PDF document data to the printer. The printer must be capable of processing
 * PDF data.
 * 
 * @author Mike Werner - <a href="https://brain-child.de">brainchild GmbH</a>
 */
public class PrintPDFDirect {

    public static void main(String[] args) throws PrintException, IOException {

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (!printerJob.printDialog()) {
            System.out.println("Printing aborted by user.");
            return;
        }

        PrintService printService = printerJob.getPrintService();

        try (FileInputStream fileInputStream = new FileInputStream(args[0])) {

            Doc pdfDocument = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);

            DocPrintJob printJob = printService.createPrintJob();

            System.out.println(String.format("Printing %s ...", args[0]));

            printJob.print(pdfDocument, new HashPrintRequestAttributeSet());

            System.out.println("Document sucessfully printed.");
        }
    }
}
