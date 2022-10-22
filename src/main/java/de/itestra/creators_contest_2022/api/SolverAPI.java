package de.itestra.creators_contest_2022.api;

import de.itestra.creators_contest_2022.model.Product;
import de.itestra.creators_contest_2022.model.Solution;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SolverAPI implements StudentsApi {

    @Override
    public ResponseEntity<Void> ping() throws Exception {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Solution> solve(Resource body) throws Exception {
        return ResponseEntity.ok(new Solution()
                .product(new Product()
                        .prices(new ArrayList<>())
                        .features(new ArrayList<>()))
                .hints(new ArrayList<>()));
    }
}

