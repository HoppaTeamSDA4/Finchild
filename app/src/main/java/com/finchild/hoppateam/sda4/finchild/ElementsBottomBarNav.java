package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public abstract class ElementsBottomBarNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
       
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigationView.postDelayed(() -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_account) {
                    Intent intent = new Intent(this, AccountChildPurchases.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_fill) {
                     startActivity(new Intent(this, AccountChildAutoFill.class));
                } else if (itemId == R.id.nav_analytics) {
                    startActivity(new Intent(this, Analytics.class));
                } else if (itemId == R.id.nav_control) {
                    startActivity(new Intent(this, Control.class));
                }
                finish();
            }, 300);

        return true;
}

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
