package ro.sd.a2.exports;

import ro.sd.a2.entity.Product;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;

import java.util.List;

public class ProductPDFExporter
{
    private List<Product> productList;

    public ProductPDFExporter(List<Product> productList)
    {
        this.productList = productList;
    }

   // private void writeTableHeader(PdfPTable table)
}
