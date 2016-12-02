package com.felipeporge.sample.di.interfaces;

/**
 * This interface represents an object that has a component.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/16
 */
public interface HasComponent<C> {

    /**
     * Gets component.
     * @return Component.
     */
    C getComponent();
}
