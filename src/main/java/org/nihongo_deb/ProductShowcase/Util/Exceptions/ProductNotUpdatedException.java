package org.nihongo_deb.ProductShowcase.Util.Exceptions;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 18.08.2023
 */
public class ProductNotUpdatedException extends RuntimeException {
    public ProductNotUpdatedException(String msg){
        super(msg);
    }
}
