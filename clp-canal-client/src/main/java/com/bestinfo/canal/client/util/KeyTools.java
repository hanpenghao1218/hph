package com.bestinfo.canal.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.Security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import com.bestinfo.canal.client.encrypt.gmhelper.BCECUtil;
import com.bestinfo.canal.client.encrypt.gmhelper.SM2Util;

public class KeyTools {
	public static String[] creatKey() throws Exception {
		// 生成SM2非对称密钥对
		KeyPair generateKeyPair = SM2Util.generateKeyPair();
		// 获取生成的公钥(64字节)
		byte[] publickey = SM2Util.getRawPublicKey((BCECPublicKey) generateKeyPair.getPublic());
		// 获取生成的私钥(用于存储)
		byte[] privatekey = ((BCECPrivateKey) generateKeyPair.getPrivate()).getEncoded();
		String[] key = new String[2];
		key[0] = new BASE64Encoder().encode(publickey).replaceAll("[\\s*\t\n\r]", "");
		key[1] = ByteUtils.toHexString(privatekey);
		System.out.println("公钥长度->" + publickey.length + ",生成公钥->" + key[0]);
		System.out.println("私钥长度->" + privatekey.length + ",生成私钥->" + key[1]);
		creatPrivateKey(key[1]);
		return key;
	}

	public static void checkKey(String publicKeyStr, String privateKeyStr) throws Exception {
		// 测试数据
		String s = "{\"terminalModel\":\"GDS688_III\",\"stationCode\":\"31010002\",\"terminalName\":\"黄埔第002号站\",\"installTime\":\"20141201000000\",\"removeTime\":\" \",\"terminalCode\":\"31010002\",\"terminalSerial\":\"3101000201\",\"terminalType\":0}";
		byte[] sign;
		byte[] signature;

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
		// 构造domain参数
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(),
				sm2ECParameters.getN());
		ECPublicKeyParameters publicKey = BCECUtil.createECPublicKeyParameters(
				new BASE64Decoder().decodeBuffer(publicKeyStr), sm2ECParameters.getCurve(), domainParameters);
		BCECPrivateKey privateKey = null;
		File file = new File(privateKeyStr);
		if (file.isFile()) {
			System.out.println("私钥路径" + privateKeyStr);
			privateKey = (BCECPrivateKey) new ObjectInputStream(new FileInputStream(file)).readObject();
		} else {
			privateKey = BCECUtil.convertPKCS8ToECPrivateKey(ByteUtils.fromHexString(privateKeyStr));
		}

		// 获取签名值
		sign = SM2Util.sign(privateKey, s.getBytes());
		signature = SM2Util.decodeDERSM2Sign(sign);
		System.out.println("签名值2：");
		System.out.println(ByteUtils.toHexString(signature));
		boolean result;
		// 验签
		// 构造domain参数
		result = SM2Util.verify(publicKey, s.getBytes(), sign);
		System.out.println("验签结果2：");
		System.out.println(result ? "成功" : "失败");
	}

	public static void creatPrivateKey(String privateKeyStr) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			BCECPrivateKey privateKey = BCECUtil.convertPKCS8ToECPrivateKey(ByteUtils.fromHexString(privateKeyStr));
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("privateKey"));
			stream.writeObject(privateKey);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		/** 生产公私钥 */
		String[] key = creatKey();
		/** 公钥串 */
		String publicKeyStr = key[0];
		/** 私钥串或路径 */
//		String privateKeyStr = "3082024b0201003081ec06072a8648ce3d02013081e0020101302c06072a8648ce3d0101022100fffffffeffffffffffffffffffffffffffffffff00000000ffffffffffffffff30440420fffffffeffffffffffffffffffffffffffffffff00000000fffffffffffffffc042028e9fa9e9d9f5e344d5a9e4bcf6509a7f39789f515ab8f92ddbcbd414d940e9304410432c4ae2c1f1981195f9904466a39c9948fe30bbff2660be1715a4589334c74c7bc3736a2f4f6779c59bdcee36b692153d0a9877cc62a474002df32e52139f0a0022100fffffffeffffffffffffffffffffffff7203df6b21c6052b53bbf40939d5412302010104820155308201510201010420f8336f89659c5db7f2dc7bfa2022d573cde1217dbaca9c12715c2c145aeb4993a081e33081e0020101302c06072a8648ce3d0101022100fffffffeffffffffffffffffffffffffffffffff00000000ffffffffffffffff30440420fffffffeffffffffffffffffffffffffffffffff00000000fffffffffffffffc042028e9fa9e9d9f5e344d5a9e4bcf6509a7f39789f515ab8f92ddbcbd414d940e9304410432c4ae2c1f1981195f9904466a39c9948fe30bbff2660be1715a4589334c74c7bc3736a2f4f6779c59bdcee36b692153d0a9877cc62a474002df32e52139f0a0022100fffffffeffffffffffffffffffffffff7203df6b21c6052b53bbf40939d54123020101a1440342000435aab9bd8ce6e4baa0f37bfd605e01532f35571a53e9de23be64e55f5a5d1d1b0840a75178ef2dfc801d766f6c60cbeee39a0e345bb5e6849574e210ec93300a";
		String privateKeyStr = "privateKey";
		checkKey(publicKeyStr, privateKeyStr);
	}

}