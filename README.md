# solr-logupdateddocuments

Small extension for logging all documents as they are submitted to solr. Please be carefull since it can accumulate to a lot of log data.

## Installation

1) download solr-logupdateddocuments.jar to $SOLR_HOME/data/lib (lib folder is not created by default)

2) install it in your solrconfig.xml for the cores that needs it. eg:

```
  <updateRequestProcessorChain name="log-updated-documents" default="true">
    <processor class="solr.LogUpdateProcessorFactory"/>    
    <processor class="com.github.zitcomdev.solr.logupdateddocuments.LogUpdatedDocumentsProcessorFactory" />
    <processor class="solr.DistributedUpdateProcessorFactory" />
    <processor class="solr.RunUpdateProcessorFactory" />
  </updateRequestProcessorChain> 
```

3)
Update log4j2.xml to log the updated docs to a seperated appender
```    
<AsyncLogger name="com.github.zitcomdev.solr.logupdateddocuments.LogUpdatedDocumentsProcessorFactory" level="all" additivity="false"> 
  <AppenderRef ref="UpdateLogFile"/>
</AsyncLogger>
```
