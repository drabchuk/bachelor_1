package nn.data;

import java.util.List;

/**
 * Created by Denis on 24.03.2017.
 */
public interface RawData {

    TrainingData toTrainingData();
    TrainingData toTrainingData(List<RawExample> rawExample);

}
