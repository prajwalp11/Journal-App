package com.prajcompany.journal.entity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class journalentry {
    @Id
    private ObjectId id;
    @Nonnull
    private String title;
    private String content;
    private LocalDateTime date;

}
