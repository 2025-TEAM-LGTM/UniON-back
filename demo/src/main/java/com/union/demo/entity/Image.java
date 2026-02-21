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

    @Column(name="s3_key", length = 255)
    private String s3Key;

    @Enumerated(EnumType.STRING)
    @Column(name="purpose", length = 255)
    private Purpose purpose;

    @Column(name="file_size")
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    @Column(name="size_type", length = 255)
    private SizeType sizeType;

    public static Image of(String s3Key, Purpose purpose, Long fileSize, SizeType sizeType){
        Image image=new Image();
        image.s3Key=s3Key;
        image.purpose=purpose;
        image.fileSize=fileSize;
        image.sizeType=sizeType;
        return image;
    }


}
