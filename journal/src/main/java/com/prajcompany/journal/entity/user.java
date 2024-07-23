package com.prajcompany.journal.entity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @Nonnull
    private String username;
    @Nonnull
    private String password;
    @DBRef
    private List<journalentry> entries=new ArrayList<>();
    private List<String> roles;
}
