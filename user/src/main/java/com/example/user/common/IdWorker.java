//package com.example.user.common;
//
//import cn.hutool.core.util.IdUtil;
//
//
//
///**
// * <p>名称：IdWorker.java</p>
// * <p>描述：分布式自增长ID</p>
// * <pre>
// *     Twitter的 Snowflake　JAVA实现方案
// * </pre>
// * 核心代码为其IdWorker这个类实现，其原理结构如下，我分别用一个0表示一位，用—分割开部分的作用：
// * 1||0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
// * 在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，
// * 然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），
// * 然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。
// * 这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID作区分），
// * 并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
// * <p>
// * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
// *
// * @author Polim
// */
//public class IdWorker implements GenId<String> {
//
//    public String genId(String table, String column) {
//        return IdUtil.getSnowflakeNextIdStr();
//    }
//
//    public static String getId() {
//        return IdUtil.getSnowflake(1, 1).nextIdStr();
//    }
//
//}