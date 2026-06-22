//package com.algaworks.ecommerce.hibernate;
//
//import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
//
//public class EcmCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
//
//    private static ThreadLocal<String> tl = new ThreadLocal<>();
//
//    /*
//    O ThreadLocal em Java serve para criar variáveis com escopo restrito
//    a uma única thread. Ele permite que diferentes threads acessem a mesma
//    variável, mas cada uma enxerga e manipula um valor isolado e
//    independente, sem interferir nos dados das outras e sem precisar de
//    sincronização manual (locks).
//     */
//
//    public static void setTenantIdentifier(String tenantId) {
//        tl.set(tenantId);
//    }
//
//    @Override
//    public String resolveCurrentTenantIdentifier() {
//        return tl.get();
//    }
//
//    @Override
//    public boolean validateExistingCurrentSessions() {
//        return false;
//    }
//}
