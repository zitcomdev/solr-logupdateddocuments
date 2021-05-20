/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.zitcomdev.solr.logupdateddocuments;


import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.update.AddUpdateCommand;
import org.apache.solr.update.processor.UpdateRequestProcessor;
import org.apache.solr.update.processor.UpdateRequestProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * A logging processor. This logs all documents that are added/updated.
 * 
 * Since this can accumulate to a lot of data please make sure log4j2 is configured accordingly.
 * </p>
 *
 */
public class LogUpdatedDocumentsProcessorFactory extends UpdateRequestProcessorFactory  {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  
 

  @Override
  public UpdateRequestProcessor getInstance(SolrQueryRequest req, SolrQueryResponse rsp, UpdateRequestProcessor next) {
	  return new LogUpdatedDocumentsProcessor(req, rsp, next);
  }
  
  
  
  static class LogUpdatedDocumentsProcessor extends UpdateRequestProcessor {

    public LogUpdatedDocumentsProcessor(SolrQueryRequest req, SolrQueryResponse rsp, UpdateRequestProcessor next) {
      super( next );
    }
    
    @Override
    public void processAdd(AddUpdateCommand cmd) throws IOException {

      // call delegate first so we can log things like the version that get set later
      if (next != null) next.processAdd(cmd);

      
      
      SolrInputDocument inputDoc = cmd.getSolrInputDocument();
      
      log.info("Updated Document ID " + cmd.getPrintableId() + " => " + inputDoc.toString());
    }

  }
}



