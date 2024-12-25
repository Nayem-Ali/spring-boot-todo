package com.example.todo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "todo")
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_todo_category")
    @JsonIgnore
    Category category;

    @PrePersist
    protected void onCreate(){
        if(this.status == null){
            this.status = false;
        }
    }
}
