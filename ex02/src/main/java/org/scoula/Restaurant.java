package org.scoula;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Setter;
@Component
@Data
@RequiredArgsConstructor //생성자
public class Restaurant {
   // @Autowired
    private Chef chef; //매개변수
}
