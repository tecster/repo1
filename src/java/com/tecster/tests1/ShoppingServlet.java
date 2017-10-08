/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecster.tests1;

import com.tecster.tests1.shopping.CD;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javier
 */
public class ShoppingServlet extends HttpServlet
{

  @Override
  public void init(ServletConfig conf) throws ServletException
  {
    System.out.println("ShoppingServlet.init");
    super.init(conf);
  }

  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs / protected void
   * processRequest(HttpServletRequest request, HttpServletResponse response)
   * throws ServletException, IOException {
   * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
   * response.getWriter(); try { // TODO output your page here. You may use
   * following sample code. out.println("<!DOCTYPE html>");
   * out.println("<html>"); out.println("<head>"); out.println("<title>Servlet
   * ShoppingServlet</title>"); out.println("</head>"); out.println("<body>");
   * out.println("<h1>Servlet ShoppingServlet at " + request.getContextPath() +
   * "</h1>"); out.println("</body>"); out.println("</html>"); } finally {
   * out.close(); }
  }
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
  {
    System.out.println("doGet...");
    //String param1= (String)session.getAttribute("param1");
    String param1 = req.getParameter("param1");
    System.out.println(param1);
    try
    {
      Thread.sleep(3000);
    } catch (InterruptedException ex)
    {
      Logger.getLogger(ShoppingServlet.class.getName()).log(Level.INFO, null, ex);
    }

    HttpSession session = req.getSession(false);
    if (session == null)
    {
      res.sendRedirect("http://localhost:8080/TestMvcModel1/error.html");
    }

    System.out.println("...doGet");
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
  {
    System.out.println("doPost...");

    HttpSession session = req.getSession(false);
    if (session == null)
    {
      res.sendRedirect("http://localhost:8080/TestMvcModel1/error.html");
    }
    List<Object> buylist = (List<Object>) session.getAttribute("shopping.shoppingcart");

    String action = req.getParameter("action");
    if (!action.equals("CHECKOUT"))
    {
      if (action.equals("DELETE"))
      {
        String del = req.getParameter("delindex");
        int d = (new Integer(del)).intValue();
        buylist.remove(d);
      } else if (action.equals("ADD"))
      {
        //any previous buys of same cd?
        boolean match = false;
        CD aCD = getCD(req);
        if (buylist == null)
        {
          //add first cd to the cart
          buylist = new ArrayList(); //first order
          buylist.add(aCD);
        } else
        { // not first buy
          for (int i = 0; i < buylist.size(); i++)
          {
            CD cd = (CD) buylist.get(i);
            if (cd.getAlbum().equals(aCD.getAlbum()))
            {
              cd.setQuantity(cd.getQuantity() + aCD.getQuantity());
              buylist.set(i, cd);
              match = true;
            } //end of if name matches
          } // end of for
          if (!match)
          {
            buylist.add(aCD);
          }
        }
      }
      session.setAttribute("shopping.shoppingcart", buylist);
      //String url="/jsp/shopping/EShop.jsp";
      String url = "/EShop.jsp";

      ServletContext sc = getServletContext();
      RequestDispatcher rd = sc.getRequestDispatcher(url);
      rd.forward(req, res);
    } else if (action.equals("CHECKOUT"))
    {
      float total = 0;
      for (int i = 0; i < buylist.size(); i++)
      {
        CD anOrder = (CD) buylist.get(i);
        float price = anOrder.getPrice();
        int qty = anOrder.getQuantity();
        total += (price * qty);
      }
      total += 0.005;
      String amount = new Float(total).toString();
      int n = amount.indexOf('.');
      amount = amount.substring(0, n + 3);
      req.setAttribute("amount", amount);
      //String url="/jsp/shopping/Checkout.jsp";
      String url = "/Checkout.jsp";

      ServletContext sc = getServletContext();
      RequestDispatcher rd = sc.getRequestDispatcher(url);
      rd.forward(req, res);
    }
    System.out.println("...doPost");
  }

  private CD getCD(HttpServletRequest req)
  {
    //imagine if all this was in a scriptlet...ugly, eh?
    String myCd = req.getParameter("CD");
    String qty = req.getParameter("qty");
    StringTokenizer t = new StringTokenizer(myCd, "|");
    String album = t.nextToken();
    String artist = t.nextToken();
    String country = t.nextToken();
    String price = t.nextToken();
    price = price.replace('$', ' ').trim();
    CD cd = new CD();
    cd.setAlbum(album);
    cd.setArtist(artist);
    cd.setCountry(country);
    cd.setPrice((new Float(price)).floatValue());
    cd.setQuantity((new Integer(qty)).intValue());
    return cd;
  }
}