package me.tatocaster.marvelapp.features.heroes.presentation.layout

import android.graphics.Typeface
import android.text.TextUtils
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import com.github.pavlospt.litho.glide.GlideImage
import me.tatocaster.marvelapp.R
import me.tatocaster.marvelapp.data.api.response.Result

@LayoutSpec
class HeroItemContentSpec {
    companion object {

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(context: ComponentContext, @Prop hero: Result): ComponentLayout = Column.create(context)
                .backgroundColor(context.resources.getColor(R.color.ghostWhite))
                .paddingDip(YogaEdge.TOP, 8f)
                .paddingDip(YogaEdge.BOTTOM, 8f)
                .child(
                        Row.create(context)
                                .child(
                                        Text.create(context)
                                                .text(hero.name)
                                                .textSizeSp(20f)
                                                .textStyle(Typeface.BOLD)
                                                .textColorRes(R.color.colorPrimary)
                                                .build())
                                .paddingDip(YogaEdge.LEFT, 8f)
                                .paddingDip(YogaEdge.RIGHT, 8f)
                                .build()
                )
                .child(
                        Row.create(context)
                                .child(
                                        Text.create(context)
                                                .text(hero.modified)
                                                .textSizeSp(14f)
                                                .textColorRes(R.color.textColor)
                                                .build())
                                .paddingDip(YogaEdge.LEFT, 8f)
                                .paddingDip(YogaEdge.RIGHT, 8f)
                                .build()
                )
                .child(
                        Row.create(context)
                                .child(
                                        Text.create(context)
                                                .text(hero.description)
                                                .ellipsize(TextUtils.TruncateAt.END)
                                                .maxLines(1)
                                                .textSizeSp(14f)
                                                .textColorRes(R.color.colorAccent)
                                                .build())
                                .paddingDip(YogaEdge.LEFT, 8f)
                                .paddingDip(YogaEdge.RIGHT, 8f)
                                .build()
                )
                .child(
                        GlideImage.create(context)
                                .imageUrl(hero.thumbnail.getFullUrl())
                                .placeholderImageRes(R.drawable.ic_launcher_background)
                                .centerCrop(true)
                                .marginDip(YogaEdge.TOP, 8f)
                                .marginDip(YogaEdge.BOTTOM, 8f)
                                .heightDip(150f)
                                .build()

                )
                .build()
    }
}