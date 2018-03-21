package co.windly.aac.test.database.daos;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import co.windly.aac.data.database.AndroidDatabase;
import co.windly.aac.data.database.daos.CoversDao;
import co.windly.aac.data.database.models.covers.CoverEntity;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class CoversDaoTest {

  @Rule
  public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  private AndroidDatabase database;

  private CoversDao coversDao;

  @Before
  public void init_db() throws Exception {
    database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AndroidDatabase.class)
        .allowMainThreadQueries()
        .build();

    coversDao = database.coversDao();
  }

  @After
  public void close_db() throws Exception {
    database.close();
  }

  @Test
  public void should_not_receive_history_when_empty() throws InterruptedException {
    // When.
    final List<CoverEntity> history = coversDao.getListOfAllCovers();

    // Then.
    assertThat(history).isNotNull();
    assertThat(history).isEmpty();
  }
}
