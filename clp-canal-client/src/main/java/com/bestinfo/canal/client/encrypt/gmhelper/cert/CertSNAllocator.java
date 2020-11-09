package com.bestinfo.canal.client.encrypt.gmhelper.cert;

import java.math.BigInteger;

public interface CertSNAllocator {
    BigInteger incrementAndGet() throws Exception;
}
