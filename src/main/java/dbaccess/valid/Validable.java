/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess.valid;

import java.sql.SQLException;

/**
 *
 * @author orlando
 */
public interface Validable {
    public abstract void valider() throws Exception;
}
