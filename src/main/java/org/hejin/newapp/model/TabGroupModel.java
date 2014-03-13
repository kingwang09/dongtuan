/*
 * @(#)TabGroupModel.java	2014. 1. 13.
 *
 * Copyright 2013 Nkia.com, Inc. All rights reserved.
 * NKIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.hejin.newapp.model;

import java.util.List;

import org.apache.tapestry5.Block;

/**
 * TabGroupModel
 *  
 * @version <tt>Revision: 1.0</tt> 2014. 1. 13.
 * @author <a href="mailto:sungjae@nkia.co.kr">Park Sung Jae</a>
 */
public interface TabGroupModel {
	void addTab(String name, String label, Block body, boolean disabled);
}
