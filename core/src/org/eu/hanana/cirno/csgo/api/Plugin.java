package org.eu.hanana.cirno.csgo.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Plugin {
    String id();
}