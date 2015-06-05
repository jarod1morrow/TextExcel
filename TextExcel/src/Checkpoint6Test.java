import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class Checkpoint6Test {

    ByteArrayOutputStream byteOut;
    PrintStream out;

    @Before
    public void setUp() {
        byteOut = new ByteArrayOutputStream();
        out = new PrintStream(byteOut);
    }

    @Test
    public void testSortRowAscendingNumbers() {
        Program program = new Program(10, 10, out);
        Sheet sheet = program.getSheet();
        sheet.setCell(1, 0, new NumberCell("7"));
        sheet.setCell(2, 0, new NumberCell("2"));
        sheet.setCell(3, 0, new NumberCell("1"));
        sheet.setCell(4, 0, new NumberCell("9"));
        sheet.setCell(5, 0, new NumberCell("5"));
        program.processLine(new Scanner("sorta B1 - F1"));
        assertEquals("1.0", sheet.cell(1, 0).eval(sheet));
        assertEquals("2.0", sheet.cell(2, 0).eval(sheet));
        assertEquals("5.0", sheet.cell(3, 0).eval(sheet));
        assertEquals("7.0", sheet.cell(4, 0).eval(sheet));
        assertEquals("9.0", sheet.cell(5, 0).eval(sheet));
    }
    
    @Test
    public void testSortColumnAscendingNumbers() {
        Program program = new Program(10, 10, out);
        Sheet sheet = program.getSheet();
        sheet.setCell(3, 1, new NumberCell("4"));
        sheet.setCell(3, 2, new NumberCell("2"));
        sheet.setCell(3, 3, new NumberCell("5"));
        sheet.setCell(3, 4, new NumberCell("3"));
        sheet.setCell(3, 5, new NumberCell("1"));
        program.processLine(new Scanner("sorta D2 - D6"));
        assertEquals("1.0", sheet.cell(3, 1).eval(sheet));
        assertEquals("2.0", sheet.cell(3, 2).eval(sheet));
        assertEquals("3.0", sheet.cell(3, 3).eval(sheet));
        assertEquals("4.0", sheet.cell(3, 4).eval(sheet));
        assertEquals("5.0", sheet.cell(3, 5).eval(sheet));
    }

    @Test
    public void testSortRowDescendingNumbers() {
        Program program = new Program(10, 10, out);
        Sheet sheet = program.getSheet();
        sheet.setCell(1, 0, new NumberCell("7"));
        sheet.setCell(2, 0, new NumberCell("2"));
        sheet.setCell(3, 0, new NumberCell("1"));
        sheet.setCell(4, 0, new NumberCell("9"));
        sheet.setCell(5, 0, new NumberCell("5"));
        program.processLine(new Scanner("sortd B1 - F1"));
        assertEquals("9.0", sheet.cell(1, 0).eval(sheet));
        assertEquals("7.0", sheet.cell(2, 0).eval(sheet));
        assertEquals("5.0", sheet.cell(3, 0).eval(sheet));
        assertEquals("2.0", sheet.cell(4, 0).eval(sheet));
        assertEquals("1.0", sheet.cell(5, 0).eval(sheet));
    }
    
    @Test
    public void testSortColumnDescendingNumbers() {
        Program program = new Program(10, 10, out);
        Sheet sheet = program.getSheet();
        sheet.setCell(3, 1, new NumberCell("4"));
        sheet.setCell(3, 2, new NumberCell("2"));
        sheet.setCell(3, 3, new NumberCell("5"));
        sheet.setCell(3, 4, new NumberCell("3"));
        sheet.setCell(3, 5, new NumberCell("1"));
        program.processLine(new Scanner("sortd D2 - D6"));
        assertEquals("5.0", sheet.cell(3, 1).eval(sheet));
        assertEquals("4.0", sheet.cell(3, 2).eval(sheet));
        assertEquals("3.0", sheet.cell(3, 3).eval(sheet));
        assertEquals("2.0", sheet.cell(3, 4).eval(sheet));
        assertEquals("1.0", sheet.cell(3, 5).eval(sheet));
    }

}
