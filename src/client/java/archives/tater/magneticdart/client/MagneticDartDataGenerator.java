package archives.tater.magneticdart.client;

import archives.tater.magneticdart.client.datagen.EntityTagGenerator;
import archives.tater.magneticdart.client.datagen.ModelGenerator;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MagneticDartDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(EntityTagGenerator::new);
	}
}
