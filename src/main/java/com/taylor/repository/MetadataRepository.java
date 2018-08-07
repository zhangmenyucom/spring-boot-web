package com.taylor.repository;

/**
 * Add some description about this class.
 *
 * @author dong.zhou
 * @since 2018/4/8 下午7:48
 */
public interface MetadataRepository<T> {

    String FIELD_METADATA = "metadata";

    T addMetadata(String id, String key, String value);

    T removeMetadata(String id, String key);
}
