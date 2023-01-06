package com.ihclglobalsearch.service

import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.impl.XMLResponseParser
import org.apache.solr.common.SolrInputDocument

fun main() {
    val solrUrl="http://localhost:8983/solr/new"
    val solr=HttpSolrClient.Builder(solrUrl).build()
    solr.parser = XMLResponseParser()
    val document=SolrInputDocument()

    document.addField("id", "123456")
    document.addField("name", "Kenmore Dishwasher")
    document.addField("price", "599.99")

    solr.add(document)
    solr.commit()
    println("Success")
}