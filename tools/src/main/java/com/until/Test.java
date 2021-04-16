package com.until;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.impl.BioinformaticsImpl;
import com.impl.CheckImpl;
import com.type.BaseData;

@Component
public class Test implements CommandLineRunner {
	@Autowired
	BaseData baseData;

	@Override
	public void run(String... args) throws Exception {
//		args = new String[] {"2","20210301-20210307"};
		if (args.length > 0) {
			baseData.init();
			Email.send = args.length >= 3;
			if (args[0].equals("1")) {
				FileList.run(baseData, args[1]);
			} else if (args[0].equals("2")) {
				CheckImpl check = new CheckImpl();
				check.run(baseData, args[1]);
			} else if (args[0].equals("3")) {
				ZipUtil.uzip(args[1] + File.separator + "testNO-" + args[1] + ".csv", args.length >= 3);
			} else if (args[0].equals("4")) {
				BioinformaticsImpl.run();
			}
			System.exit(0);
		}
	}
}