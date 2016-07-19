package apkmarvel.realmcrudsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import apkmarvel.realmcrudsample.model.Book;
import apkmarvel.realmcrudsample.realm.RealmController;
import io.realm.Realm;
import io.realm.RealmResults;

/*https://realm.io/docs/java/latest/*/
public class MainActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.realm = RealmController.with(this).getRealm();
    }
    public void create(View v){
        Book book = new Book();
        //book.setId(RealmController.getInstance().getBooks().size() + 1);
        book.setId(RealmController.getInstance().getBooks().size() + System.currentTimeMillis());
        book.setTitle("sammple");
        book.setAuthor("sammple");
        book.setImageUrl("sammple");

        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();
    }

    public void read(View v){
        RealmResults<Book> bookList = RealmController.with(this).getBooks();
        for(int i=0;i<bookList.size();i++){
            Book book = bookList.get(i);
            Toast.makeText(MainActivity.this, book.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void delete(View v){
        RealmResults<Book> bookList = RealmController.with(this).getBooks();
        bookList.remove(0);
        realm.commitTransaction();
    }
}
