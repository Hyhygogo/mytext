package cn.itcast.jd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jd.pojo.Product;
import cn.itcast.jd.pojo.Result;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private HttpSolrServer httpSolrServer;

	@Override
	public Result queryProduct(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception
			 {
		// 创建solrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询关键词
		if (StringUtils.isNotBlank(queryString)) {
			solrQuery.setQuery(queryString);
		} else {
			solrQuery.setQuery("*:*");
		}
		// 设置默认域
		solrQuery.set("df", "product_keywords");
		// 设置商品过滤条件
		// 设置商品分类名称
		if (StringUtils.isNotBlank(catalog_name)) {
			catalog_name = "product_catalog_name:" + catalog_name;
		}
		// 设置价格
		if (StringUtils.isNotBlank(price)) {
			String[] sp = price.split("-");
			if (sp != null && sp.length == 2) {
				price = "product_price:[" + sp[0] + " TO " + sp[1] + "]";
			}
		}
		solrQuery.setFilterQueries(catalog_name, price);
		// 商品排序如果是1，正序排序
		if ("1".equals(sort)) {
			solrQuery.setSort("product_price", ORDER.asc);
		} else {
			solrQuery.setSort("product_price", ORDER.desc);
		}
		// 设置分页
		if (page == null) {
			page = 1;
		}
		solrQuery.setStart(page);
		solrQuery.setRows(20);
		// 设置高亮
		// 显示高亮
		solrQuery.setHighlight(true);
		// 高亮字段
		solrQuery.addHighlightField("product_name");
		// 头
		solrQuery.setHighlightSimplePre("<font color='red'>");
		// 尾部
		solrQuery.setHighlightSimplePost("</font>");
		// 查询数据
		QueryResponse response = this.httpSolrServer.query(solrQuery);
		// 获取查询总记录数
		SolrDocumentList results = response.getResults();
		long total  = results.getNumFound();

		// "highlighting": {
		// "177": {
		// "product_name": [
		// "家天下透明情侣挂钩-<em>蜡笔</em>小新"
		// ]
		// },
		// "475": {
		// "product_name": [
		// "家天下<em>蜡笔</em>小新浴室四件套"
		// ]
		// },
		// 获取高亮显示数据
		Map<String, Map<String, List<String>>> map = response.getHighlighting();
		List<Product> products = new ArrayList<>();
		// 解析结果集，放到Prodcut中
		for (SolrDocument solrDocument : results) {
			Product product = new Product();
			product.setPid(solrDocument.get("id").toString());
			List<String> list = map.get(solrDocument.get("id")).get("product_name");
			if (list != null && list.size() > 0) {
				product.setName(list.get(0));
			} else {
				product.setName(solrDocument.get("product_name").toString());

			}
			product.setPicture(solrDocument.get("product_picture").toString());
			product.setPrice(solrDocument.get("product_price").toString());
			products.add(product);
		}
		// 封装
		Result result = new Result();

		result.setCurPage(page);
		result.setRecordCount(total);
		result.setProductList(products);
		// 总页数计算公式(total+rows-1)/rows
		result.setPageCount((int) (total + 20 - 1) / 20);

		return result;
	}

}
