package com.union.demo.entity;
import com.union.demo.enums.Purpose;
import com.union.demo.enums.SizeType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long imageId;

    @Column(name="image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="purpose")
    private Purpose purpose;

    @Column(name="file_size")
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    @Column(name="size_type")
    private SizeType sizeType;

    public void updateImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }


}
