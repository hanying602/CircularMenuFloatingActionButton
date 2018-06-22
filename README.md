# CircularMenuFloatingActionButton

![image](https://github.com/hanying602/CircularMenuFloatingActionButton/blob/master/app/src/main/res/drawable/preview.gif)

## Getting Started

### Installation

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
    ...
    maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency

```
dependencies {
    implementation 'com.github.hanying602:CircularMenuFloatingActionButton:v1.1'
}
```

### Usage

1.
```
...
<com.larvata.library.CircularMenuFloatingActionButton
        android:id="@+id/main_activity_mfab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        .../>
...
```

2.
```
FloatingActionButton fab1 = new FloatingActionButton(MainActivity.this);
fab1.setImageResource(...);
fab1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //...
      }
});

FloatingActionButton fab2 = new FloatingActionButton(MainActivity.this);
fab2.setImageResource(...);
fab2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //...
      }
});

FloatingActionButton fab3 = new FloatingActionButton(MainActivity.this);
fab3.setImageResource(...);
fab3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //...
      }
});

FloatingActionButton fab4 = new FloatingActionButton(MainActivity.this);
fab4.setImageResource(...);
fab4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //...
      }
});

FloatingActionButton fab5 = new FloatingActionButton(MainActivity.this);
fab5.setImageResource(...);
fab5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //...
      }
});
```

3.
```
ConstraintLayout rootLayout = findViewById(R.id.main_activity_layout);
rootLayout.addView(fab1);
rootLayout.addView(fab2);
rootLayout.addView(fab3);
rootLayout.addView(fab4);
rootLayout.addView(fab5);
```

4.
```
CircularMenuFloatingActionButton mFab = findViewById(R.id.main_activity_mfab);
mFab.setParentView(rootLayout);
mFab.setMenuButtons(fab1,fab2,fab3,fab4,fab5);
mFab.setCustomSize(150);
mFab.setMenuButtonsSize(100);
mFab.setBtnDistance(300);
```

