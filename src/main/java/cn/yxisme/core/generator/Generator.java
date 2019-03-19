package cn.yxisme.core.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generator {

	private void generator() throws Exception{
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		//指定 逆向工程配置文件
		String path = System.getProperty("user.dir");
		String confPath = path.concat(File.separator).concat("src").concat(File.separator).concat("main")
				.concat(File.separator).concat("resources").concat(File.separator).concat("generator").concat(File.separator).concat("generatorConfig.xml");
		File configFile = new File(confPath);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}

	public static void main(String[] args) throws Exception {
		try {
			Generator generator = new Generator();
			generator.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
