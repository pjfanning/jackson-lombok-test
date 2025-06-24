package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.junit.jupiter.api.Test;

public class AnySetterTest {

    @Test
    public void test() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("key", "value");
        attributeMap.put("key2", "value2");
        final CollectionType type = CollectionType.builder().name("name")
                .items(Collections.singletonList(
                        Item.builder().type("type").attributes(attributeMap).build()))
                .build();
        final String json = mapper.writeValueAsString(type);
        System.out.println(json);
        final CollectionType serialized = mapper.readValue(json, CollectionType.class);
        assertNotNull(serialized.getItems().get(0).getAttributes());
    }

    @JsonDeserialize(
            builder = CollectionType.CollectionTypeBuilder.class)
    public static class CollectionType {
        private String name;
        private List<Item> items;

        @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
        public static class CollectionTypeBuilder {
            private String name;
            private List<Item> items;

            CollectionTypeBuilder() {
            }

            /**
             * @return {@code this}.
             */
            public CollectionType.CollectionTypeBuilder name(final String name) {
                this.name = name;
                return this;
            }

            /**
             * @return {@code this}.
             */
            public CollectionType.CollectionTypeBuilder items(final List<Item> items) {
                this.items = items;
                return this;
            }

            public CollectionType build() {
                return new CollectionType(this.name, this.items);
            }

            @Override
            public String toString() {
                return "CollectionType.CollectionTypeBuilder(name=" + this.name + ", items=" + this.items
                        + ")";
            }
        }

        public static CollectionType.CollectionTypeBuilder builder() {
            return new CollectionType.CollectionTypeBuilder();
        }

        public String getName() {
            return this.name;
        }

        public List<Item> getItems() {
            return this.items;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setItems(final List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CollectionType)) {
                return false;
            }
            final CollectionType other = (CollectionType) o;
            if (!other.canEqual(this)) {
                return false;
            }
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
                return false;
            }
            final Object this$items = this.getItems();
            final Object other$items = other.getItems();
            if (this$items == null ? other$items != null : !this$items.equals(other$items)) {
                return false;
            }
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof CollectionType;
        }

        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            final Object $items = this.getItems();
            return result * PRIME + ($items == null ? 43 : $items.hashCode());
        }

        @Override
        public String toString() {
            return "CollectionType(name=" + this.getName() + ", items=" + this.getItems() + ")";
        }

        public CollectionType() {
        }

        public CollectionType(final String name, final List<Item> items) {
            this.name = name;
            this.items = items;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(builder = Item.ItemBuilder.class)
    public static class Item {
        private String type;
        @JsonAnyGetter
        @JsonAnySetter
        private Map<String, Object> attributes;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
        public static class ItemBuilder {
            private String type;
            private Map<String, Object> attributes;

            ItemBuilder() {
            }

            /**
             * @return {@code this}.
             */
            public Item.ItemBuilder type(final String type) {
                this.type = type;
                return this;
            }

            /**
             * @return {@code this}.
             */
            public Item.ItemBuilder attributes(final Map<String, Object> attributes) {
                this.attributes = attributes;
                return this;
            }

            public Item build() {
                return new Item(this.type, this.attributes);
            }

            @Override
            public String toString() {
                return "Item.ItemBuilder(type=" + this.type + ", attributes=" + this.attributes + ")";
            }
        }

        public static Item.ItemBuilder builder() {
            return new Item.ItemBuilder();
        }

        public String getType() {
            return this.type;
        }

        public Map<String, Object> getAttributes() {
            return this.attributes;
        }

        public void setType(final String type) {
            this.type = type;
        }

        public void setAttributes(final Map<String, Object> attributes) {
            this.attributes = attributes;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Item)) {
                return false;
            }
            final Item other = (Item) o;
            if (!other.canEqual(this)) {
                return false;
            }
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
                return false;
            }
            final Object this$attributes = this.getAttributes();
            final Object other$attributes = other.getAttributes();
            if (this$attributes == null ? other$attributes != null : !this$attributes.equals(other$attributes)) {
                return false;
            }
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Item;
        }

        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $type = this.getType();
            result = result * PRIME + ($type == null ? 43 : $type.hashCode());
            final Object $attributes = this.getAttributes();
            return result * PRIME + ($attributes == null ? 43 : $attributes.hashCode());
        }

        @Override
        public String toString() {
            return "Item(type=" + this.getType() + ", attributes=" + this.getAttributes() + ")";
        }

        public Item() {
        }

        public Item(final String type, final Map<String, Object> attributes) {
            this.type = type;
            this.attributes = attributes;
        }
    }

}