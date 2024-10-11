package com.moxos.uab.config.propierties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostgresPropierties {
    private String url;
    private String username;
    private String password;
}
