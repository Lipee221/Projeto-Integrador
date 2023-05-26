
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConnectionFactory.ConnectionFactory;
import models.Input;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author 42labinfo
 */
public class InputDao {
    
    public void create(Input entrada) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement(
         "insert into entradas(peso_e, custo_e, quantidade_e, criacao_e, atualizacao_e, status_e, id_ingrediente) values (?,?,?,?,?,?,?)") ;
            sql.setFloat(1, entrada.getWeight());
            sql.setFloat(2, entrada.getCost());
            sql.setInt(3, entrada.getQuantity());
            sql.setString(4, entrada.getToStringCreation());
            sql.setString(5, entrada.getToStringUpdate());
            sql.setString(6, entrada.getStatus());
            sql.setInt(7, entrada.getId_ingredient());
            sql.executeUpdate();
            
            JOptionPane.showMessageDialog(
                    null, "Cadasdtrado com sucesso!");
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(
                   null, "Erro ao Cadastrar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, sql);
        }
        
    }
    
    
    public List<Input> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Input> entradas = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT * FROM entradas");
            rs = sql.executeQuery();
            while(rs.next()){
                Input entrada = new Input();
                entrada.setId(rs.getInt("id_entrada"));
                entrada.setWeight(rs.getFloat("peso_e"));
                entrada.setCost(rs.getFloat("custo_e"));
                entrada.setQuantity(rs.getInt("quantidade_e"));
                entrada.setToLocalDateTimeCreation(rs.getString("criacao_e"));
                entrada.setToLocalDateTimeUpdate(rs.getString("atualizacao_e"));
                entrada.setStatus(rs.getString("status_e"));
                entrada.setId_ingredient(rs.getInt("id_ingrediente"));
                entradas.add(entrada);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return entradas;
      
    }
    
    
    public void update(Input entrada) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        try{
            sql = con.prepareStatement("update entradas set peso_e = ?, custo_e = ?, quantidade_e = ?, criacao_e = ?, atualizacao_e = ?, status_e = ?, id_ingrediente = ? where id = ?");
            sql.setFloat(1, entrada.getWeight());
            sql.setFloat(2, entrada.getCost());
            sql.setInt(3, entrada.getQuantity());
            sql.setString(4, entrada.getToStringCreation());
            sql.setString(5, entrada.getToStringUpdate());
            sql.setString(7, entrada.getStatus());
            sql.setInt(8, entrada.getId_ingredient());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql,rs);
        }
    }
    
    public void delete(Input entradas) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        
        try{
            sql = con.prepareStatement("DELETE FROM entradas WHERE id = ?");
            sql.setInt(1, entradas.getId());
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "sucesso");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            ConnectionFactory.closeConnection(con,sql);
        }
    }
    
     
    public List<Input> readBusca(String busca) throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement sql = null;
        ResultSet rs = null;
        
        List<Input> entradas = new ArrayList<>();
        try{
            sql = con.prepareStatement("SELECT input.*, ingredients.nameI FROM snack INNER JOIN ingredients ON input.idIngredient = ingredients.ingredientId WHERE ingredients.nameI LIKE ?;");
            sql.setString(1, "%"+busca+"%");
            rs = sql.executeQuery();
            while(rs.next()){
               Input entrada = new Input();
                entrada.setId(rs.getInt("id_entrada"));
                entrada.setWeight(rs.getFloat("peso_e"));
                entrada.setCost(rs.getFloat("custo_e"));
                entrada.setQuantity(rs.getInt("quantidade_e"));
                entrada.setToLocalDateTimeCreation(rs.getString("criacao_e"));
                entrada.setToLocalDateTimeUpdate(rs.getString("atualizacao_e"));
                entrada.setStatus(rs.getString("status_e"));
                entrada.setId_ingredient(rs.getInt("id_ingrediente"));
                entradas.add(entrada);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            ConnectionFactory.closeConnection(con, sql, rs);
        }
        return entradas; 
    }

}