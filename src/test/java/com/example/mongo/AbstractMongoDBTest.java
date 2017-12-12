package com.example.mongo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import junit.framework.TestCase;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public abstract class AbstractMongoDBTest extends TestCase {

	private static final String MONGODB_HOST = "localhost";
	private static final int MONGODB_PORT = 12345;
	/**
	 * please store Starter or RuntimeConfig in a static final field
	 * if you want to use artifact store caching (or else disable caching)
	 */
	private static final MongodStarter starter = MongodStarter.getDefaultInstance();

	private static MongodExecutable _mongodExe;
	private static MongodProcess _mongod;

//	private MongoClient _mongo;

	@BeforeClass
	public static void initializeMongo() throws Exception {

		_mongodExe = starter.prepare(new MongodConfigBuilder()
				.version( Version.Main.PRODUCTION)
				.net(new Net(MONGODB_HOST, MONGODB_PORT, Network.localhostIsIPv6()))
				.build());
		_mongod = _mongodExe.start();

	}

	@AfterClass
	public static void destroyMongo() throws Exception {
		_mongod.stop();
		_mongodExe.stop();
	}
}
